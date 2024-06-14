package com.business.intelligence.service;

import com.business.intelligence.service.controller.PersonController;
import com.business.intelligence.service.controller.StageController;
import com.business.intelligence.service.model.building.Project;
import com.business.intelligence.service.model.building.Stage;
import com.business.intelligence.service.model.building.Task;
import com.business.intelligence.service.model.person.Person;
import com.business.intelligence.service.model.person.Role;
import com.business.intelligence.service.repository.PersonRepository;
import com.business.intelligence.service.repository.ProjectRepository;
import com.business.intelligence.service.repository.StageRepository;
import com.business.intelligence.service.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@SpringBootTest
public class ConnectionTest {

    @Autowired
    PersonRepository personRepository;
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    PersonController personController;

    @Autowired
    StageController stageController;
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void connectionTest() {
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
        taskRepository.save(task);

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
    }

    @Test
    public void testControllers() throws Exception {
        final Person foreman = new Person();
        foreman.setFirstName("maksim");
        foreman.setSurname("kerunov");
        foreman.setFatherName("evgenievich");
        foreman.setRole(Role.FOREMAN);
        foreman.setLogin("login");
        foreman.setPassword("password");
        personRepository.save(foreman);

        final Task task = new Task();
        task.setName("Бетонирование");
        task.setStartDate(Instant.now());
        task.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        task.setExpectedEndDate(Instant.parse("2025-06-01T00:00:01Z"));
        task.setWorkers(List.of(foreman));
        taskRepository.save(task);


        final Stage stage = new Stage();
        stage.setName("stage");
        stage.setStartDate(Instant.now());
        stage.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        stage.setExpectedEndDate(Instant.parse("2026-06-01T00:00:01Z"));
        stage.setTasks(List.of(task));


        final Stage updatedStage = new Stage();
        updatedStage.setId(1);
        updatedStage.setName("updated");
        updatedStage.setStartDate(Instant.now());
        updatedStage.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        updatedStage.setExpectedEndDate(Instant.parse("2026-06-01T00:00:01Z"));
        updatedStage.setTasks(null);


        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "OK");
        stageController.create(httpHeaders, stage);

        final Task newTask = new Task();
        newTask.setName("Бетонирование");
        newTask.setStartDate(Instant.now());
        newTask.setActualEndDate(Instant.parse("2024-06-01T00:00:01Z"));
        newTask.setExpectedEndDate(Instant.parse("2025-06-01T00:00:01Z"));

        taskRepository.save(newTask);
        ResponseEntity<Stage> entityFind = stageController.addTask(httpHeaders, stage.getId(), newTask);
    }
}
