package com.business.intelligence.service.controller;

import com.business.intelligence.service.exception.NotFoundException;
import com.business.intelligence.service.exception.WrongRequestException;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.model.building.Task;
import com.business.intelligence.service.repository.StageRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/stage")
public class StageController {

    private final StageRepository stageRepository;

    public StageController(final StageRepository stageRepository) {
        this.stageRepository = stageRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<Stage> create(@RequestHeader final HttpHeaders headers,
                                        @RequestBody final Stage stage) throws Exception {
        stageRepository.save(stage);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<Stage> getById(@RequestHeader final HttpHeaders headers,
                                         @PathVariable("id") final int id) throws Exception {
        Optional<Stage> result = stageRepository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<List<Stage>> getAll(@RequestHeader final HttpHeaders headers) throws Exception {
        return ResponseEntity.ok(stageRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Stage> update(@RequestHeader final HttpHeaders headers,
                                        @PathVariable("id") final int id, @RequestBody final Stage entity) {

        if (id != entity.getId()) {
            throw new WrongRequestException("Id and body id mismatch");
        }


        final Optional<Stage> stage = stageRepository.findById(id);
        if (stage.isEmpty()) {
            throw new NotFoundException("Stage not found with id =" + id);
        }

        return ResponseEntity.ok(stageRepository.save(entity));
    }

    @RequestMapping(value = "/{id}/add-task", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Stage> addTask(@RequestHeader final HttpHeaders headers,
                                         @PathVariable("id") final int id, @RequestBody final Task task) {
        final Optional<Stage> stage = stageRepository.findById(id);
        if (stage.isEmpty()) {
            throw new NotFoundException("Stage not found with id =" + id);
        }
        stage.get().getTasks().add(task);
        Stage result = stageRepository.save(stage.get());

        return ResponseEntity.ok(result);
    }
}
