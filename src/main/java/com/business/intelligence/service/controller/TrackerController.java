package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.telemetry.Telemetry;
import com.business.intelligence.service.model.trackers.Tracker;
import com.business.intelligence.service.repository.TelemetryRepository;
import com.business.intelligence.service.repository.TrackerRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/trackers")
public class TrackerController {

    private TelemetryRepository telemetryRepository;
    private TrackerRepository trackerRepository;

    TrackerController(final TelemetryRepository telemetryRepository, final TrackerRepository trackerRepository) {
        this.telemetryRepository = telemetryRepository;
        this.trackerRepository = trackerRepository;
    }

    @RequestMapping(value = "/getAllTrackers", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tracker>> getAllTrackers(@RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(trackerRepository.findAll());
    }

    @RequestMapping(value = "/getLastTelemetryByImei/{imei}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Telemetry> getLastTelemetry(@RequestHeader HttpHeaders headers,
                                                      @PathVariable String imei) {

        final Optional<Tracker> trackerOptional = trackerRepository.findByImei(imei);
        if (trackerOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(telemetryRepository.findAllByImeiOrderByTimeDesc(imei).get(0));
    }
}
