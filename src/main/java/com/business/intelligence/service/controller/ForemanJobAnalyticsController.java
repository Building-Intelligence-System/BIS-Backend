package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.building.Task;
import com.business.intelligence.service.model.foremananalytics.ForemanAnalyticsDto;
import com.business.intelligence.service.model.person.Person;
import com.business.intelligence.service.repository.TaskRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/foreman-analytics")
public class ForemanJobAnalyticsController {

    final TaskRepository taskRepository;

    public ForemanJobAnalyticsController(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/getAnalytics", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ForemanAnalyticsDto>> getForemanJobAnalytics(@RequestHeader final HttpHeaders headers) {

        final Map<Person, List<Task>> personsTasks = taskRepository.findAll()
                .stream().collect(Collectors.groupingBy(Task::getHead, Collectors.toList()));

        if (personsTasks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        final List<ForemanAnalyticsDto> result = new ArrayList<>();
        for (Map.Entry<Person, List<Task>> entry : personsTasks.entrySet()) {
            final List<Long> differences = new ArrayList<>();

            for (Task task : entry.getValue()) {
                if (task.getActualEndDate() == null) {
                    continue;
                }
                long currDiff = task.getExpectedEndDate().getEpochSecond() - task.getActualEndDate().getEpochSecond();
                differences.add(currDiff);
            }
            result.add(new ForemanAnalyticsDto(entry.getKey().getId(), (differences.stream().reduce(0L, Long::sum)) / differences.size()));
        }

        return ResponseEntity.ok(result);
    }
}
