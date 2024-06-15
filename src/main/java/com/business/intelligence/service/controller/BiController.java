package com.business.intelligence.service.controller;

import com.business.intelligence.service.dto.ProjectBiInfoDto;
import com.business.intelligence.service.exception.NotFoundException;
import com.business.intelligence.service.model.building.Project;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.repository.ProjectRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/bi")
public class BiController {

    private final ProjectRepository projectRepository;

    public BiController(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @RequestMapping(value = "/project/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ProjectBiInfoDto> projectBI(@PathVariable("id") final int id) {
        final Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project not found with id =" + id);
        }

        final List<Stage> stages = project.get().getStages();
        final Optional<Stage> lastStage = stages.stream()
                .max(Comparator.comparing(Stage::getExpectedEndDate));

        final Instant startDate = project.get().getStartDate();

        final long precent100 = lastStage.get().getExpectedEndDate().getEpochSecond() - startDate.getEpochSecond();

        final ProjectBiInfoDto projectBiInfoDto = new ProjectBiInfoDto();
        projectBiInfoDto.setProjectId(project.get().getId());

        if (Instant.now().getEpochSecond() < startDate.getEpochSecond()) {
            long precentX = Instant.now().getEpochSecond() - startDate.getEpochSecond();
            projectBiInfoDto.setExpectedDonePercent(precentX * 100.0 / precent100);
        } else {
            projectBiInfoDto.setExpectedDonePercent(100.0);
        }

        long durationDelta = lastStage.get().getExpectedEndDate().getEpochSecond() - startDate.getEpochSecond();
        for (final Stage stage : stages) {
            if (stage.getActualEndDate() == null) {
                durationDelta -= stage.getExpectedEndDate().getEpochSecond() - stage.getStartDate().getEpochSecond();
            } else {
                durationDelta -= stage.getActualEndDate().getEpochSecond() - stage.getStartDate().getEpochSecond();
            }
        }

        projectBiInfoDto.setActualDonePercent(durationDelta * 1.0);

        return ResponseEntity.ok(projectBiInfoDto);
    }
}
