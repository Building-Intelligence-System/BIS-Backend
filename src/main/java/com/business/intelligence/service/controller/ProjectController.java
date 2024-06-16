package com.business.intelligence.service.controller;

import com.business.intelligence.service.exception.NotFoundException;
import com.business.intelligence.service.model.building.Project;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.repository.ProjectRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/project")
public class ProjectController {

    private final ProjectRepository projectRepository;

    public ProjectController(final ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> create(@RequestHeader final HttpHeaders headers,
                                          @RequestBody final Project project) throws Exception {
        projectRepository.save(project);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> getById(@RequestHeader final HttpHeaders headers,
                                           @PathVariable("id") final int id) throws Exception {
        Optional<Project> result = projectRepository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getAll(@RequestHeader final HttpHeaders headers) throws Exception {
        return ResponseEntity.ok(projectRepository.findAll());
    }

    @RequestMapping(value = "/{id}/add-stage", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Project> addStage(@PathVariable("id") final int id,
                                            @RequestBody final Stage stage) {
        final Optional<Project> project = projectRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project not found with id =" + id);
        }
        project.get().getStages().add(stage);
        Project result = projectRepository.save(project.get());

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@RequestHeader final HttpHeaders headers,
                                       @PathVariable("id") final int id) throws Exception {
        Optional<Project> result = projectRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        projectRepository.delete(result.get());
        return ResponseEntity.ok().build();
    }
}
