package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.shifts.PersonShiftDto;
import com.business.intelligence.service.model.shifts.PersonShifts;
import com.business.intelligence.service.repository.PersonShiftsRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/person-shifts")
public class PersonShiftsController {

    private final PersonShiftsRepository personShiftsRepository;

    public PersonShiftsController(final PersonShiftsRepository personShiftsRepository) {
        this.personShiftsRepository = personShiftsRepository;
    }

    @RequestMapping(value = "/getAvgComingTimeById/{workerId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonShiftDto> getAverageComingTimeById(@RequestHeader final HttpHeaders headers,
                                                                   @PathVariable int workerId) {
        final Instant instantLimit = Instant.now().minus(30, ChronoUnit.DAYS);
        final List<LocalTime> comingInstants = personShiftsRepository.findAllByPersonId((workerId))
                .stream().filter(shift -> !shift.isEnding()).filter(shift -> shift.getScanTime().isAfter(instantLimit))
                .map(shift -> LocalTime.ofInstant(shift.getScanTime(), ZoneId.systemDefault())).toList();

        if (comingInstants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        long nanoSum = 0L;
        for (LocalTime comingInstant : comingInstants) {
            nanoSum += comingInstant.toNanoOfDay();
        }

        return ResponseEntity.ok(new PersonShiftDto(workerId, LocalTime.ofNanoOfDay(nanoSum / comingInstants.size())));
    }

    @RequestMapping(value = "/getAvgEndingTimeById/{workerId}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonShiftDto> getAverageEndingTimeById(@RequestHeader final HttpHeaders headers,
                                                                   @PathVariable int workerId) {
        final Instant instantLimit = Instant.now().minus(30, ChronoUnit.DAYS);
        final List<LocalTime> endingInstants = personShiftsRepository.findAllByPersonId(workerId)
                .stream().filter(PersonShifts::isEnding).filter(shift -> shift.getScanTime().isAfter(instantLimit))
                .map(shift -> LocalTime.ofInstant(shift.getScanTime(), ZoneId.systemDefault())).toList();

        if (endingInstants.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        long nanoSum = 0L;
        for (LocalTime endingInstant : endingInstants) {
            nanoSum += endingInstant.toNanoOfDay();
        }

        return ResponseEntity.ok(new PersonShiftDto(workerId, LocalTime.ofNanoOfDay(nanoSum / endingInstants.size())));
    }

    @RequestMapping(value = "/getAvgComingTimeByIds", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonShiftDto>> getAverageComingTimeByIds(@RequestHeader final HttpHeaders headers,
                                                                          @RequestBody List<Integer> ids) {
        final Instant instantLimit = Instant.now().minus(30, ChronoUnit.DAYS);

        final Map<Integer, List<PersonShifts>> personsShifts = personShiftsRepository.findAllByPersonIds((ids))
                .stream().filter(shift -> !shift.isEnding()).filter(shift -> shift.getScanTime().isAfter(instantLimit))
                .collect(Collectors.groupingBy(PersonShifts::getPersonId, Collectors.toList()));

        final List<PersonShiftDto> result = new ArrayList<>();
        for (Map.Entry<Integer, List<PersonShifts>> entry : personsShifts.entrySet()) {
            List<LocalTime> comingTimes = new ArrayList<>();
            for (PersonShifts shift : entry.getValue()) {
                comingTimes.add(LocalTime.ofInstant(shift.getScanTime(), ZoneId.systemDefault()));
            }
            if (comingTimes.isEmpty()) {
                continue;
            }

            long nanoSum = 0L;
            for (LocalTime comingInstant : comingTimes) {
                nanoSum += comingInstant.toNanoOfDay();
            }
            result.add(new PersonShiftDto(entry.getKey(), LocalTime.ofNanoOfDay(nanoSum / comingTimes.size())));
        }

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/getAvgEndingTimeByIds", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonShiftDto>> getAverageEndingTimeById(@RequestHeader final HttpHeaders headers,
                                                                         @RequestBody List<Integer> ids) {
        final Instant instantLimit = Instant.now().minus(30, ChronoUnit.DAYS);

        final Map<Integer, List<PersonShifts>> personsShifts = personShiftsRepository.findAllByPersonIds((ids))
                .stream().filter(PersonShifts::isEnding).filter(shift -> shift.getScanTime().isAfter(instantLimit))
                .collect(Collectors.groupingBy(PersonShifts::getPersonId, Collectors.toList()));

        final List<PersonShiftDto> result = new ArrayList<>();
        for (Map.Entry<Integer, List<PersonShifts>> entry : personsShifts.entrySet()) {
            List<LocalTime> endingTimes = new ArrayList<>();
            for (PersonShifts shift : entry.getValue()) {
                endingTimes.add(LocalTime.ofInstant(shift.getScanTime(), ZoneId.systemDefault()));
            }
            if (endingTimes.isEmpty()) {
                continue;
            }

            long nanoSum = 0L;
            for (LocalTime comingInstant : endingTimes) {
                nanoSum += comingInstant.toNanoOfDay();
            }
            result.add(new PersonShiftDto(entry.getKey(), LocalTime.ofNanoOfDay(nanoSum / endingTimes.size())));
        }

        return ResponseEntity.ok(result);
    }
}
