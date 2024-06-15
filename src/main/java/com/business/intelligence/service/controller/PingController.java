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
        worker.setFirstName("Алексей");
        worker.setSurname("Морозов");
        worker.setFatherName("Евгеньевич");
        worker.setRole(Role.WORKER);
        personRepository.save(worker);

        final Person foreman = new Person();
        foreman.setFirstName("Максим");
        foreman.setSurname("Керунов");
        foreman.setFatherName("Евгеньевич");
        foreman.setRole(Role.FOREMAN);
        personRepository.save(foreman);

        final Task task = new Task();
        task.setName("Изучение грунта");
        task.setStartDate(Instant.parse("2020-05-01T00:00:01Z"));
        task.setActualEndDate(Instant.parse("2024-05-27T00:00:01Z"));
        task.setExpectedEndDate(Instant.parse("2024-05-30T00:00:01Z"));
        task.setWorkers(List.of(foreman));
        taskRepository.save(task);

        task.setWorkers(List.of(foreman));
        task.setHead(foreman);

        personRepository.save(foreman);
        personRepository.save(worker);

        final Person stageHead = new Person();
        stageHead.setFirstName("Артур");
        stageHead.setSurname("Мушулов");
        stageHead.setFatherName("Викторович");
        stageHead.setRole(Role.FOREMAN);
        personRepository.save(stageHead);

        final Stage stage = new Stage();
        stage.setName("Подготовительный этап");
        stage.setStartDate(Instant.parse("2019-05-01T00:00:01Z"));
        stage.setActualEndDate(Instant.parse("2020-02-01T00:00:01Z"));
        stage.setExpectedEndDate(Instant.parse("2019-12-24T00:00:01Z"));
        stage.setTasks(List.of(task));
        stage.setHead(stageHead);
        stageRepository.save(stage);

        final Project project = new Project();
        project.setName("Строительство многоквартирного дома в г.Краснодаре, ул. Северной, д.15");
        project.setStartDate(Instant.parse("2019-05-01T00:00:01Z"));
        project.setExpectedEndDate(Instant.parse("2024-05-01T00:00:01Z"));
        project.setActualEndDate(Instant.parse("2024-02-01T00:00:01Z"));
        project.setStages(List.of(stage));
        projectRepository.save(project);

        return ResponseEntity.ok().build();
    }
}
