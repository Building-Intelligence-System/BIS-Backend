
package com.business.intelligence.service.controller;

import com.business.intelligence.service.exception.NotFoundException;
import com.business.intelligence.service.exception.WrongRequestException;
import com.business.intelligence.service.model.building.Comment;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.model.building.Task;
import com.business.intelligence.service.model.person.Person;
import com.business.intelligence.service.repository.TaskRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskRepository taskRepository;

    public TaskController(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<Task> create(@RequestHeader final HttpHeaders headers,
                                       @RequestBody final Task stage) throws Exception {
        return ResponseEntity.ok(taskRepository.save(stage));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<Task> getById(@RequestHeader final HttpHeaders headers,
                                         @PathVariable("id") final int id) throws Exception {
        Optional<Task> result = taskRepository.findById(id);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = IMAGE_PNG_VALUE)
    public ResponseEntity<List<Task>> getAll(@RequestHeader final HttpHeaders headers) throws Exception {
        return ResponseEntity.ok(taskRepository.findAll());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> update(
            @PathVariable("id") final int id,
            @RequestBody final Task entity) {
        if (id != entity.getId()) {
            throw new WrongRequestException("id and body id mismatch");
        }

        final Optional<Task> project = taskRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Stage not found with id =" + id);
        }

        return ResponseEntity.ok(taskRepository.save(entity));
    }

    @RequestMapping(value = "/{id}/add-worker", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> addWorker(@PathVariable("id") final int id,
                                           @RequestBody final Person worker) {
        final Optional<Task> project = taskRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project not found with id =" + id);
        }
        project.get().getWorkers().add(worker);
        final Task result = taskRepository.save(project.get());

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}/add-comment", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> addComment(@PathVariable("id") final int id,
                                           @RequestBody final Comment comment) {
        final Optional<Task> project = taskRepository.findById(id);
        if (project.isEmpty()) {
            throw new NotFoundException("Project not found with id =" + id);
        }
        project.get().getComments().add(comment);
        final Task result = taskRepository.save(project.get());

        return ResponseEntity.ok(result);
    }
}
