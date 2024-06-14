package com.business.intelligence.service.controller;

import com.business.intelligence.service.model.building.Project;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.model.building.Task;
import com.business.intelligence.service.model.person.Person;
import com.business.intelligence.service.model.person.Role;
import com.business.intelligence.service.repository.PersonRepository;
import com.business.intelligence.service.repository.ProjectRepository;
import com.business.intelligence.service.repository.StageRepository;
import com.business.intelligence.service.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
public class PingController {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    ProjectRepository projectRepository;

    @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestHeader final HttpHeaders headers) throws Exception {
        return ResponseEntity.ok("pong");
    }

    @RequestMapping(value = "/init-test-data", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> init(@RequestHeader final HttpHeaders headers) throws Exception {

        final Person worker = new Person();
        worker.setFirstName("alex");
        worker.setSurname("morozov");
        worker.setFatherName("evgenievich");
        worker.setRole(Role.WORKER);
        personRepository.save(worker);

        final Person foreman = new Person();
        foreman.setFirstName("maksim");
        foreman.setSurname("kerunov");
        foreman.setFatherName("evgenievich");
        foreman.setRole(Role.FOREMAN);
        personRepository.save(foreman);

        final Task task = new Task();
        task.setName("Бетонирование");
        task.setStartDate(Instant.now());
        task.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        task.setExpectedEndDate(Instant.parse("2025-06-01T00:00:01Z"));
        task.setWorkers(List.of(foreman));
        taskRepository.save(task);

        task.setWorkers(List.of(foreman));
        task.setHead(foreman);

        personRepository.save(foreman);
        personRepository.save(worker);

        final Person stageHead = new Person();
        stageHead.setFirstName("artur");
        stageHead.setSurname("hehe");
        stageHead.setFatherName("evgenievich");
        stageHead.setRole(Role.FOREMAN);
        personRepository.save(stageHead);

        final Stage stage = new Stage();
        stage.setName("stage");
        stage.setStartDate(Instant.now());
        stage.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        stage.setExpectedEndDate(Instant.parse("2026-06-01T00:00:01Z"));
        stage.setTasks(List.of(task));
        stage.setHead(stageHead);
        stageRepository.save(stage);

        final Project project = new Project();
        project.setName("project");
        project.setStartDate(Instant.now());
        project.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        project.setStages(List.of(stage));
        projectRepository.save(project);


        return ResponseEntity.ok().build();
    }
}
