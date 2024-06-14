package com.business.intelligence.service;

import com.business.intelligence.service.dao.PersonRepository;
import com.business.intelligence.service.dao.ProjectRepository;
import com.business.intelligence.service.dao.StageRepository;
import com.business.intelligence.service.dao.TaskRepository;
import com.business.intelligence.service.model.constructionregion.ConstructionType;
import com.business.intelligence.service.model.constructionregion.Project;
import com.business.intelligence.service.model.constructionregion.Stage;
import com.business.intelligence.service.model.constructionregion.Task;
import com.business.intelligence.service.model.people.Person;
import com.business.intelligence.service.model.people.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
        task.setStart(Instant.now());
        task.setEnd(Instant.parse("2024-06-01T00:00:01Z"));
        task.setExpectedDuration(100);
        taskRepository.save(task);

        worker.setTask(task);
        foreman.setTask(task);
        task.setHead(foreman);
        task.setWorkers(List.of(worker));

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
        stage.setStart(Instant.now());
        stage.setEnd(Instant.parse("2024-06-01T00:00:01Z"));
        stage.setExpectedDuration(1000);
        stage.setTasks(List.of(task));
        stage.setHead(stageHead);
        stageRepository.save(stage);


        final Project project = new Project();
        project.setName("project");
        project.setEnd(Instant.now());
        project.setEnd(Instant.parse("2024-06-01T00:00:01Z"));
        project.setExpectedDuration(100000);
        project.setStages(List.of(stage));
        project.setLatitude(27.29);
        project.setLongitude(27.29);
        project.setAddress("kykyshkina");
        project.setType(ConstructionType.RESIDENTIAL);
        projectRepository.save(project);
    }
}
