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
        task.setStartDate(Instant.parse("2020-05-01T00:00:00Z"));
        task.setActualEndDate(Instant.parse("2024-05-27T00:00:00Z"));
        task.setExpectedEndDate(Instant.parse("2024-05-30T00:00:00Z"));
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
        stage.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stage.setActualEndDate(Instant.parse("2020-02-01T00:00:00Z"));
        stage.setExpectedEndDate(Instant.parse("2019-12-24T00:00:00Z"));
        stage.setTasks(List.of(task));
        stage.setHead(stageHead);
        stageRepository.save(stage);

        final Project project = new Project();
        project.setName("Строительство многоквартирного дома в г.Краснодаре, ул. Северной, д.15");
        project.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        project.setExpectedEndDate(Instant.parse("2024-05-01T00:00:00Z"));
        project.setActualEndDate(Instant.parse("2024-02-01T00:00:00Z"));
        project.setStages(List.of(stage));
        projectRepository.save(project);

        return ResponseEntity.ok().build();
    }


    @RequestMapping(value = "/get-test-projects", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Project>> getTestProjects() {

        // region PROJECT 1

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectOne = new Person();
        foremanTaskStageOneOneProjectOne.setFirstName("Вадим");
        foremanTaskStageOneOneProjectOne.setSurname("Керунов");
        foremanTaskStageOneOneProjectOne.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectOne);

        final Task taskOneStageOneProjectOne = new Task();
        taskOneStageOneProjectOne.setName("Изучение грунта");
        taskOneStageOneProjectOne.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectOne.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectOne.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectOne.setHead(foremanTaskStageOneOneProjectOne);
        taskRepository.save(taskOneStageOneProjectOne);

        final Person foremanTaskTwoStageOneProjectOne = new Person();
        foremanTaskTwoStageOneProjectOne.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectOne.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectOne.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectOne);

        final Task taskTwoStageOneProjectOne = new Task();
        taskTwoStageOneProjectOne.setName("Расчистка территорий");
        taskTwoStageOneProjectOne.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectOne.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectOne.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectOne.setHead(foremanTaskTwoStageOneProjectOne);
        taskRepository.save(taskTwoStageOneProjectOne);

        final Person foremanTaskThreeStageOneProjectOne = new Person();
        foremanTaskThreeStageOneProjectOne.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectOne.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectOne.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectOne);

        final Task taskThreeStageOneProjectOne = new Task();
        taskThreeStageOneProjectOne.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectOne.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectOne.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectOne.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectOne.setHead(foremanTaskThreeStageOneProjectOne);
        taskRepository.save(taskThreeStageOneProjectOne);

        final Person foremanTaskFourStageOneProject = new Person();
        foremanTaskFourStageOneProject.setFirstName("Вениамин");
        foremanTaskFourStageOneProject.setSurname("Немо");
        foremanTaskFourStageOneProject.setFatherName("Игнатьевич");
        foremanTaskFourStageOneProject.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageOneProject);

        final Task taskFourStageOneProjectOne = new Task();
        taskFourStageOneProjectOne.setName("Планирование зон");
        taskFourStageOneProjectOne.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectOne.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectOne.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectOne.setHead(foremanTaskFourStageOneProject);
        taskRepository.save(taskFourStageOneProjectOne);

        final Person foremanStageOneProjectOne = new Person();
        foremanStageOneProjectOne.setFirstName("Илларион");
        foremanStageOneProjectOne.setSurname("Веселов");
        foremanStageOneProjectOne.setFatherName("Алексеевич");
        foremanStageOneProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectOne);

        final Stage stageOneProjectOne = new Stage();
        stageOneProjectOne.setName("Подготовительный этап");
        stageOneProjectOne.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectOne.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectOne.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectOne.setTasks(List.of(taskOneStageOneProjectOne, taskTwoStageOneProjectOne, taskThreeStageOneProjectOne, taskFourStageOneProjectOne));
        stageOneProjectOne.setHead(foremanStageOneProjectOne);
        stageRepository.save(stageOneProjectOne);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectOne = new Person();
        foremanTaskOneStageTwoProjectOne.setFirstName("Илья");
        foremanTaskOneStageTwoProjectOne.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectOne.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectOne);

        final Task taskOneStageTwoProjectOne = new Task();
        taskOneStageTwoProjectOne.setName("Изучение архивных материалов");
        taskOneStageTwoProjectOne.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectOne.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectOne.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectOne.setHead(foremanTaskOneStageTwoProjectOne);
        taskOneStageTwoProjectOne.setWorkers(List.of(foremanTaskOneStageTwoProjectOne));
        taskRepository.save(taskOneStageTwoProjectOne);

        final Person foremanTaskTwoStageTwoProjectOne = new Person();
        foremanTaskTwoStageTwoProjectOne.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectOne.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectOne.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectOne);

        final Task taskTwoStageTwoProjectOne = new Task();
        taskTwoStageTwoProjectOne.setName("Получение образцов пород");
        taskTwoStageTwoProjectOne.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectOne.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectOne.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectOne.setHead(foremanTaskTwoStageTwoProjectOne);
        taskTwoStageTwoProjectOne.setWorkers(List.of(foremanTaskTwoStageTwoProjectOne));
        taskRepository.save(taskTwoStageTwoProjectOne);


        final Person foremanTaskThreeStageTwoProjectOne = new Person();
        foremanTaskThreeStageTwoProjectOne.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectOne.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectOne.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectOne);

        final Task taskThreeStageTwoProjectOne = new Task();
        taskThreeStageTwoProjectOne.setName("Лабораторные исследования");
        taskThreeStageTwoProjectOne.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectOne.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectOne.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectOne.setHead(foremanTaskThreeStageTwoProjectOne);
        taskThreeStageTwoProjectOne.setWorkers(List.of(foremanTaskThreeStageTwoProjectOne));
        taskRepository.save(taskThreeStageTwoProjectOne);

        final Person foremanStageTwoProjectOne = new Person();
        foremanStageTwoProjectOne.setFirstName("Алексей");
        foremanStageTwoProjectOne.setSurname("Морозов");
        foremanStageTwoProjectOne.setFatherName("Евгеньевич");
        foremanStageTwoProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectOne);

        final Stage stageTwoProjectOne = new Stage();
        stageTwoProjectOne.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectOne.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectOne.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectOne.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectOne.setTasks(List.of(taskOneStageTwoProjectOne, taskTwoStageTwoProjectOne, taskThreeStageTwoProjectOne));
        stageTwoProjectOne.setHead(foremanStageTwoProjectOne);
        stageRepository.save(stageTwoProjectOne);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectOne = new Person();
        foremanTaskOneStageThreeProjectOne.setFirstName("Максим");
        foremanTaskOneStageThreeProjectOne.setSurname("Керунов");
        foremanTaskOneStageThreeProjectOne.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectOne);

        final Task taskOneStageThreeProjectOne = new Task();
        taskOneStageThreeProjectOne.setName("Составление задания на проектирование");
        taskOneStageThreeProjectOne.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectOne.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectOne.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectOne.setHead(foremanTaskOneStageThreeProjectOne);
        taskRepository.save(taskOneStageThreeProjectOne);

        final Person foremanTaskTwoStageThreeProjectOne = new Person();
        foremanTaskTwoStageThreeProjectOne.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectOne.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectOne.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectOne);

        final Task taskTwoStageThreeProjectOne = new Task();
        taskTwoStageThreeProjectOne.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectOne.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectOne.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectOne.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectOne.setHead(foremanTaskTwoStageThreeProjectOne);
        taskRepository.save(taskTwoStageThreeProjectOne);

        final Person foremanTaskThreeStageThreeProjectOne = new Person();
        foremanTaskThreeStageThreeProjectOne.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectOne.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectOne.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectOne);

        final Task taskThreeStageThreeProjectOne = new Task();
        taskThreeStageThreeProjectOne.setName("Разработка проектной документации");
        taskThreeStageThreeProjectOne.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectOne.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectOne.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectOne.setHead(foremanTaskThreeStageThreeProjectOne);
        taskRepository.save(taskThreeStageThreeProjectOne);

        final Person foremanTaskFourStageThreeProject = new Person();
        foremanTaskFourStageThreeProject.setFirstName("Максим");
        foremanTaskFourStageThreeProject.setSurname("Кобол");
        foremanTaskFourStageThreeProject.setFatherName("Артурович");
        foremanTaskFourStageThreeProject.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProject);

        final Task taskFourStageThreeProjectOne = new Task();
        taskFourStageThreeProjectOne.setName("Разработка рабочей документации");
        taskFourStageThreeProjectOne.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectOne.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectOne.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectOne.setHead(foremanTaskFourStageThreeProject);
        taskRepository.save(taskFourStageThreeProjectOne);

        final Person foremanTaskFiveStageThreeProject = new Person();
        foremanTaskFiveStageThreeProject.setFirstName("Александр");
        foremanTaskFiveStageThreeProject.setSurname("Чернышов");
        foremanTaskFiveStageThreeProject.setFatherName("Алексеевич");
        foremanTaskFiveStageThreeProject.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageThreeProject);

        final Task taskFiveStageThreeProjectOne = new Task();
        taskFiveStageThreeProjectOne.setName("Экспертиза");
        taskFiveStageThreeProjectOne.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectOne.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectOne.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectOne.setHead(foremanTaskFiveStageThreeProject);
        taskRepository.save(taskFiveStageThreeProjectOne);

        final Person foremanTaskSixStageThreeProject = new Person();
        foremanTaskSixStageThreeProject.setFirstName("Артур");
        foremanTaskSixStageThreeProject.setSurname("Первышов");
        foremanTaskSixStageThreeProject.setFatherName("Маликович");
        foremanTaskSixStageThreeProject.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageThreeProject);

        final Task taskSixStageThreeProjectOne = new Task();
        taskSixStageThreeProjectOne.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectOne.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectOne.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectOne.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectOne.setHead(foremanTaskSixStageThreeProject);
        taskRepository.save(taskSixStageThreeProjectOne);


        final Person foremanStageThreeProjectOne = new Person();
        foremanStageThreeProjectOne.setFirstName("Алексей");
        foremanStageThreeProjectOne.setSurname("Морозов");
        foremanStageThreeProjectOne.setFatherName("Евгеньевич");
        foremanStageThreeProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectOne);

        final Stage stageThreeProjectOne = new Stage();
        stageThreeProjectOne.setName("Проектирование здания");
        stageThreeProjectOne.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectOne.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectOne.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectOne.setTasks(List.of(taskOneStageThreeProjectOne, taskTwoStageThreeProjectOne, taskThreeStageThreeProjectOne, taskSixStageThreeProjectOne));
        stageThreeProjectOne.setHead(foremanStageThreeProjectOne);
        stageRepository.save(stageThreeProjectOne);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectOne = new Person();
        foremanTaskOneStageFourProjectOne.setFirstName("Вадим");
        foremanTaskOneStageFourProjectOne.setSurname("Владим");
        foremanTaskOneStageFourProjectOne.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectOne);

        final Task taskOneStageFourProjectOne = new Task();
        taskOneStageFourProjectOne.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectOne.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectOne.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectOne.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectOne.setHead(foremanTaskOneStageFourProjectOne);
        taskOneStageFourProjectOne.setWorkers(List.of(foremanTaskOneStageFourProjectOne));
        taskRepository.save(taskOneStageFourProjectOne);

        final Person foremanTaskTwoStageFourProjectOne = new Person();
        foremanTaskTwoStageFourProjectOne.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectOne.setSurname("Морозов");
        foremanTaskTwoStageFourProjectOne.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectOne);

        final Task taskTwoStageFourProjectOne = new Task();
        taskTwoStageFourProjectOne.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectOne.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectOne.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectOne.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectOne.setHead(foremanTaskTwoStageFourProjectOne);
        taskTwoStageFourProjectOne.setWorkers(List.of(foremanTaskTwoStageFourProjectOne));
        taskRepository.save(taskTwoStageFourProjectOne);

        final Person foremanTaskThreeStageFourProjectOne = new Person();
        foremanTaskThreeStageFourProjectOne.setFirstName("Петр");
        foremanTaskThreeStageFourProjectOne.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectOne.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectOne);

        final Task taskThreeStageFourProjectOne = new Task();
        taskThreeStageFourProjectOne.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectOne.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectOne.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectOne.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectOne.setHead(foremanTaskThreeStageFourProjectOne);
        taskThreeStageFourProjectOne.setWorkers(List.of(foremanTaskThreeStageFourProjectOne));
        taskRepository.save(taskThreeStageFourProjectOne);

        final Person foremanTaskFourStageFourProjectOne = new Person();
        foremanTaskFourStageFourProjectOne.setFirstName("Захар");
        foremanTaskFourStageFourProjectOne.setSurname("Бурунов");
        foremanTaskFourStageFourProjectOne.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectOne);

        final Task taskFourStageFourProjectOne = new Task();
        taskFourStageFourProjectOne.setName("Строительство крыши и навесов");
        taskFourStageFourProjectOne.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectOne.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectOne.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectOne.setHead(foremanTaskFourStageFourProjectOne);
        taskFourStageFourProjectOne.setWorkers(List.of(foremanTaskFourStageFourProjectOne));
        taskRepository.save(taskFourStageFourProjectOne);

        final Person foremanTaskFiveStageFourProjectOne = new Person();
        foremanTaskFiveStageFourProjectOne.setFirstName("Влад");
        foremanTaskFiveStageFourProjectOne.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectOne.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectOne);

        final Task taskFiveStageFourProjectOne = new Task();
        taskFiveStageFourProjectOne.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectOne.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectOne.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectOne.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectOne.setHead(foremanTaskFiveStageFourProjectOne);
        taskFiveStageFourProjectOne.setWorkers(List.of(foremanTaskFiveStageFourProjectOne));
        taskRepository.save(taskFiveStageFourProjectOne);

        final Person foremanTaskSixStageFourProjectOne = new Person();
        foremanTaskSixStageFourProjectOne.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectOne.setSurname("Носов");
        foremanTaskSixStageFourProjectOne.setFatherName("Олегович");
        foremanTaskSixStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectOne);

        final Task taskSixStageFourProjectOne = new Task();
        taskSixStageFourProjectOne.setName("Проведение коммуникаций");
        taskSixStageFourProjectOne.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectOne.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectOne.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectOne.setHead(foremanTaskSixStageFourProjectOne);
        taskSixStageFourProjectOne.setWorkers(List.of(foremanTaskSixStageFourProjectOne));
        taskRepository.save(taskSixStageFourProjectOne);

        final Person foremanTaskSevenStageFourProjectOne = new Person();
        foremanTaskSevenStageFourProjectOne.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectOne.setSurname("Морозов");
        foremanTaskSevenStageFourProjectOne.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectOne);

        final Task taskSevenStageFourProjectOne = new Task();
        taskSevenStageFourProjectOne.setName("Внутренняя отделка");
        taskSevenStageFourProjectOne.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectOne.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectOne.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectOne.setHead(foremanTaskSevenStageFourProjectOne);
        taskRepository.save(taskSevenStageFourProjectOne);

        final Person foremanTaskEightStageFourProjectOne = new Person();
        foremanTaskEightStageFourProjectOne.setFirstName("Иван");
        foremanTaskEightStageFourProjectOne.setSurname("Мегчов");
        foremanTaskEightStageFourProjectOne.setFatherName("Петрович");
        foremanTaskEightStageFourProjectOne.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectOne);

        final Task taskEightStageFourProjectOne = new Task();
        taskEightStageFourProjectOne.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectOne.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectOne.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectOne.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectOne.setHead(foremanTaskEightStageFourProjectOne);
        taskRepository.save(taskEightStageFourProjectOne);

        final Stage stageFourProjectOne = new Stage();
        stageFourProjectOne.setName("Возведение здания");
        stageFourProjectOne.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectOne.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectOne.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectOne.setTasks(List.of(taskOneStageFourProjectOne, taskTwoStageFourProjectOne, taskFiveStageFourProjectOne, taskEightStageFourProjectOne));
        stageFourProjectOne.setHead(foremanTaskEightStageFourProjectOne);
        stageRepository.save(stageFourProjectOne);

        // endregion STAGE 4

        final Project firstProject = new Project();
        firstProject.setName("Строительство многоквартирного дома в г.Краснодаре, ул. Северной, д.15");
        firstProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        firstProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        firstProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        firstProject.setStages(List.of(stageOneProjectOne, stageTwoProjectOne, stageThreeProjectOne, stageFourProjectOne));
        projectRepository.save(firstProject);


        // endregion PROJECT 1
        // region PROJECT 2

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectTwo = new Person();
        foremanTaskStageOneOneProjectTwo.setFirstName("Вадим");
        foremanTaskStageOneOneProjectTwo.setSurname("Керунов");
        foremanTaskStageOneOneProjectTwo.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectTwo);

        final Task taskOneStageOneProjectTwo = new Task();
        taskOneStageOneProjectTwo.setName("Изучение грунта");
        taskOneStageOneProjectTwo.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectTwo.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectTwo.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectTwo.setHead(foremanTaskStageOneOneProjectTwo);
        taskRepository.save(taskOneStageOneProjectTwo);

        final Person foremanTaskTwoStageOneProjectTwo = new Person();
        foremanTaskTwoStageOneProjectTwo.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectTwo.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectTwo.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectTwo);

        final Task taskTwoStageOneProjectTwo = new Task();
        taskTwoStageOneProjectTwo.setName("Расчистка территорий");
        taskTwoStageOneProjectTwo.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectTwo.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectTwo.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectTwo.setHead(foremanTaskTwoStageOneProjectTwo);
        taskRepository.save(taskTwoStageOneProjectTwo);

        final Person foremanTaskThreeStageOneProjectTwo = new Person();
        foremanTaskThreeStageOneProjectTwo.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectTwo.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectTwo.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectTwo);

        final Task taskThreeStageOneProjectTwo = new Task();
        taskThreeStageOneProjectTwo.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectTwo.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectTwo.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectTwo.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectTwo.setHead(foremanTaskThreeStageOneProjectTwo);
        taskRepository.save(taskThreeStageOneProjectTwo);

        final Person foremanTaskFourStageProjectTwo = new Person();
        foremanTaskFourStageProjectTwo.setFirstName("Вениамин");
        foremanTaskFourStageProjectTwo.setSurname("Немо");
        foremanTaskFourStageProjectTwo.setFatherName("Игнатьевич");
        foremanTaskFourStageProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageProjectTwo);

        final Task taskFourStageOneProjectTwo = new Task();
        taskFourStageOneProjectTwo.setName("Планирование зон");
        taskFourStageOneProjectTwo.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectTwo.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectTwo.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectTwo.setHead(foremanTaskFourStageProjectTwo);
        taskRepository.save(taskFourStageOneProjectTwo);

        final Person foremanStageOneProjectTwo = new Person();
        foremanStageOneProjectTwo.setFirstName("Илларион");
        foremanStageOneProjectTwo.setSurname("Веселов");
        foremanStageOneProjectTwo.setFatherName("Алексеевич");
        foremanStageOneProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectTwo);

        final Stage stageOneProjectTwo = new Stage();
        stageOneProjectTwo.setName("Подготовительный этап");
        stageOneProjectTwo.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectTwo.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectTwo.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectTwo.setTasks(List.of(taskOneStageOneProjectTwo, taskTwoStageOneProjectTwo, taskThreeStageOneProjectTwo, taskFourStageOneProjectTwo));
        stageOneProjectTwo.setHead(foremanStageOneProjectTwo);
        stageRepository.save(stageOneProjectTwo);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectTwo = new Person();
        foremanTaskOneStageTwoProjectTwo.setFirstName("Илья");
        foremanTaskOneStageTwoProjectTwo.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectTwo.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectTwo);

        final Task taskOneStageTwoProjectTwo = new Task();
        taskOneStageTwoProjectTwo.setName("Изучение архивных материалов");
        taskOneStageTwoProjectTwo.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectTwo.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectTwo.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectTwo.setHead(foremanTaskOneStageTwoProjectTwo);
        taskOneStageTwoProjectTwo.setWorkers(List.of(foremanTaskOneStageTwoProjectTwo));
        taskRepository.save(taskOneStageTwoProjectTwo);

        final Person foremanTaskTwoStageTwoProjectTwo = new Person();
        foremanTaskTwoStageTwoProjectTwo.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectTwo.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectTwo.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectTwo);

        final Task taskTwoStageTwoProjectTwo = new Task();
        taskTwoStageTwoProjectTwo.setName("Получение образцов пород");
        taskTwoStageTwoProjectTwo.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectTwo.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectTwo.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectTwo.setHead(foremanTaskTwoStageTwoProjectTwo);
        taskTwoStageTwoProjectTwo.setWorkers(List.of(foremanTaskTwoStageTwoProjectTwo));
        taskRepository.save(taskTwoStageTwoProjectTwo);


        final Person foremanTaskThreeStageTwoProjectTwo = new Person();
        foremanTaskThreeStageTwoProjectTwo.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectTwo.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectTwo.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectTwo);

        final Task taskThreeStageTwoProjectTwo = new Task();
        taskThreeStageTwoProjectTwo.setName("Лабораторные исследования");
        taskThreeStageTwoProjectTwo.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectTwo.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectTwo.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectTwo.setHead(foremanTaskThreeStageTwoProjectTwo);
        taskThreeStageTwoProjectTwo.setWorkers(List.of(foremanTaskThreeStageTwoProjectTwo));
        taskRepository.save(taskThreeStageTwoProjectTwo);

        final Person foremanStageTwoProjectTwo = new Person();
        foremanStageTwoProjectTwo.setFirstName("Алексей");
        foremanStageTwoProjectTwo.setSurname("Морозов");
        foremanStageTwoProjectTwo.setFatherName("Евгеньевич");
        foremanStageTwoProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectTwo);

        final Stage stageTwoProjectTwo = new Stage();
        stageTwoProjectTwo.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectTwo.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectTwo.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectTwo.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectTwo.setTasks(List.of(taskOneStageTwoProjectTwo, taskTwoStageTwoProjectTwo, taskThreeStageTwoProjectTwo));
        stageTwoProjectTwo.setHead(foremanStageTwoProjectTwo);
        stageRepository.save(stageTwoProjectTwo);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectTwo = new Person();
        foremanTaskOneStageThreeProjectTwo.setFirstName("Максим");
        foremanTaskOneStageThreeProjectTwo.setSurname("Керунов");
        foremanTaskOneStageThreeProjectTwo.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectTwo);

        final Task taskOneStageThreeProjectTwo = new Task();
        taskOneStageThreeProjectTwo.setName("Составление задания на проектирование");
        taskOneStageThreeProjectTwo.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectTwo.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectTwo.setHead(foremanTaskOneStageThreeProjectTwo);
        taskRepository.save(taskOneStageThreeProjectTwo);

        final Person foremanTaskTwoStageThreeProjectTwo = new Person();
        foremanTaskTwoStageThreeProjectTwo.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectTwo.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectTwo.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectTwo);

        final Task taskTwoStageThreeProjectTwo = new Task();
        taskTwoStageThreeProjectTwo.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectTwo.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectTwo.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectTwo.setHead(foremanTaskTwoStageThreeProjectTwo);
        taskRepository.save(taskTwoStageThreeProjectTwo);

        final Person foremanTaskThreeStageThreeProjectTwo = new Person();
        foremanTaskThreeStageThreeProjectTwo.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectTwo.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectTwo.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectTwo);

        final Task taskThreeStageThreeProjectTwo = new Task();
        taskThreeStageThreeProjectTwo.setName("Разработка проектной документации");
        taskThreeStageThreeProjectTwo.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectTwo.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectTwo.setHead(foremanTaskThreeStageThreeProjectTwo);
        taskRepository.save(taskThreeStageThreeProjectTwo);

        final Person foremanTaskFourStageThreeProjectTwo = new Person();
        foremanTaskFourStageThreeProjectTwo.setFirstName("Максим");
        foremanTaskFourStageThreeProjectTwo.setSurname("Кобол");
        foremanTaskFourStageThreeProjectTwo.setFatherName("Артурович");
        foremanTaskFourStageThreeProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProjectTwo);

        final Task taskFourStageThreeProjectTwo = new Task();
        taskFourStageThreeProjectTwo.setName("Разработка рабочей документации");
        taskFourStageThreeProjectTwo.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectTwo.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectTwo.setHead(foremanTaskFourStageThreeProjectTwo);
        taskRepository.save(taskFourStageThreeProjectTwo);

        final Person foremanTaskFiveStageProjectTwo = new Person();
        foremanTaskFiveStageProjectTwo.setFirstName("Александр");
        foremanTaskFiveStageProjectTwo.setSurname("Чернышов");
        foremanTaskFiveStageProjectTwo.setFatherName("Алексеевич");
        foremanTaskFiveStageProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageProjectTwo);

        final Task taskFiveStageThreeProjectTwo = new Task();
        taskFiveStageThreeProjectTwo.setName("Экспертиза");
        taskFiveStageThreeProjectTwo.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectTwo.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectTwo.setHead(foremanTaskFiveStageProjectTwo);
        taskRepository.save(taskFiveStageThreeProjectTwo);

        final Person foremanTaskSixStageProjectTwo = new Person();
        foremanTaskSixStageProjectTwo.setFirstName("Артур");
        foremanTaskSixStageProjectTwo.setSurname("Первышов");
        foremanTaskSixStageProjectTwo.setFatherName("Маликович");
        foremanTaskSixStageProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageProjectTwo);

        final Task taskSixStageThreeProjectTwo = new Task();
        taskSixStageThreeProjectTwo.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectTwo.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectTwo.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectTwo.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectTwo.setHead(foremanTaskSixStageProjectTwo);
        taskRepository.save(taskSixStageThreeProjectTwo);


        final Person foremanStageThreeProjectTwo = new Person();
        foremanStageThreeProjectTwo.setFirstName("Алексей");
        foremanStageThreeProjectTwo.setSurname("Морозов");
        foremanStageThreeProjectTwo.setFatherName("Евгеньевич");
        foremanStageThreeProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectTwo);

        final Stage stageThreeProjectTwo = new Stage();
        stageThreeProjectTwo.setName("Проектирование здания");
        stageThreeProjectTwo.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectTwo.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectTwo.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectTwo.setTasks(List.of(taskOneStageThreeProjectTwo, taskTwoStageThreeProjectTwo, taskThreeStageThreeProjectTwo, taskSixStageThreeProjectTwo));
        stageThreeProjectTwo.setHead(foremanStageThreeProjectTwo);
        stageRepository.save(stageThreeProjectTwo);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectTwo = new Person();
        foremanTaskOneStageFourProjectTwo.setFirstName("Вадим");
        foremanTaskOneStageFourProjectTwo.setSurname("Владим");
        foremanTaskOneStageFourProjectTwo.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectTwo);

        final Task taskOneStageFourProjectTwo = new Task();
        taskOneStageFourProjectTwo.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectTwo.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectTwo.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectTwo.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectTwo.setHead(foremanTaskOneStageFourProjectTwo);
        taskOneStageFourProjectTwo.setWorkers(List.of(foremanTaskOneStageFourProjectTwo));
        taskRepository.save(taskOneStageFourProjectTwo);

        final Person foremanTaskTwoStageFourProjectTwo = new Person();
        foremanTaskTwoStageFourProjectTwo.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectTwo.setSurname("Морозов");
        foremanTaskTwoStageFourProjectTwo.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectTwo);

        final Task taskTwoStageFourProjectTwo = new Task();
        taskTwoStageFourProjectTwo.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectTwo.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectTwo.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectTwo.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectTwo.setHead(foremanTaskTwoStageFourProjectTwo);
        taskTwoStageFourProjectTwo.setWorkers(List.of(foremanTaskTwoStageFourProjectTwo));
        taskRepository.save(taskTwoStageFourProjectTwo);

        final Person foremanTaskThreeStageFourProjectTwo = new Person();
        foremanTaskThreeStageFourProjectTwo.setFirstName("Петр");
        foremanTaskThreeStageFourProjectTwo.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectTwo.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectTwo);

        final Task taskThreeStageFourProjectTwo = new Task();
        taskThreeStageFourProjectTwo.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectTwo.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectTwo.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectTwo.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectTwo.setHead(foremanTaskThreeStageFourProjectTwo);
        taskThreeStageFourProjectTwo.setWorkers(List.of(foremanTaskThreeStageFourProjectTwo));
        taskRepository.save(taskThreeStageFourProjectTwo);

        final Person foremanTaskFourStageFourProjectTwo = new Person();
        foremanTaskFourStageFourProjectTwo.setFirstName("Захар");
        foremanTaskFourStageFourProjectTwo.setSurname("Бурунов");
        foremanTaskFourStageFourProjectTwo.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectTwo);

        final Task taskFourStageFourProjectTwo = new Task();
        taskFourStageFourProjectTwo.setName("Строительство крыши и навесов");
        taskFourStageFourProjectTwo.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectTwo.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectTwo.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectTwo.setHead(foremanTaskFourStageFourProjectTwo);
        taskFourStageFourProjectTwo.setWorkers(List.of(foremanTaskFourStageFourProjectTwo));
        taskRepository.save(taskFourStageFourProjectTwo);

        final Person foremanTaskFiveStageFourProjectTwo = new Person();
        foremanTaskFiveStageFourProjectTwo.setFirstName("Влад");
        foremanTaskFiveStageFourProjectTwo.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectTwo.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectTwo);

        final Task taskFiveStageFourProjectTwo = new Task();
        taskFiveStageFourProjectTwo.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectTwo.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectTwo.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectTwo.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectTwo.setHead(foremanTaskFiveStageFourProjectTwo);
        taskFiveStageFourProjectTwo.setWorkers(List.of(foremanTaskFiveStageFourProjectTwo));
        taskRepository.save(taskFiveStageFourProjectTwo);

        final Person foremanTaskSixStageFourProjectTwo = new Person();
        foremanTaskSixStageFourProjectTwo.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectTwo.setSurname("Носов");
        foremanTaskSixStageFourProjectTwo.setFatherName("Олегович");
        foremanTaskSixStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectTwo);

        final Task taskSixStageFourProjectTwo = new Task();
        taskSixStageFourProjectTwo.setName("Проведение коммуникаций");
        taskSixStageFourProjectTwo.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectTwo.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectTwo.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectTwo.setHead(foremanTaskSixStageFourProjectTwo);
        taskSixStageFourProjectTwo.setWorkers(List.of(foremanTaskSixStageFourProjectTwo));
        taskRepository.save(taskSixStageFourProjectTwo);

        final Person foremanTaskSevenStageFourProjectTwo = new Person();
        foremanTaskSevenStageFourProjectTwo.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectTwo.setSurname("Морозов");
        foremanTaskSevenStageFourProjectTwo.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectTwo);

        final Task taskSevenStageFourProjectTwo = new Task();
        taskSevenStageFourProjectTwo.setName("Внутренняя отделка");
        taskSevenStageFourProjectTwo.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectTwo.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectTwo.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectTwo.setHead(foremanTaskSevenStageFourProjectTwo);
        taskRepository.save(taskSevenStageFourProjectTwo);

        final Person foremanTaskEightStageFourProjectTwo = new Person();
        foremanTaskEightStageFourProjectTwo.setFirstName("Иван");
        foremanTaskEightStageFourProjectTwo.setSurname("Мегчов");
        foremanTaskEightStageFourProjectTwo.setFatherName("Петрович");
        foremanTaskEightStageFourProjectTwo.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectTwo);

        final Task taskEightStageFourProjectTwo = new Task();
        taskEightStageFourProjectTwo.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectTwo.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectTwo.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectTwo.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectTwo.setHead(foremanTaskEightStageFourProjectTwo);
        taskRepository.save(taskEightStageFourProjectTwo);

        final Stage stageFourProjectTwo = new Stage();
        stageFourProjectTwo.setName("Возведение здания");
        stageFourProjectTwo.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectTwo.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectTwo.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectTwo.setTasks(List.of(taskOneStageFourProjectTwo, taskTwoStageFourProjectTwo, taskFiveStageFourProjectTwo, taskEightStageFourProjectTwo));
        stageFourProjectTwo.setHead(foremanTaskEightStageFourProjectTwo);
        stageRepository.save(stageFourProjectTwo);

        // endregion STAGE 4

        final Project secondProject = new Project();
        secondProject.setName("Строительство многоквартирного дома в г.Славянск-на-Кубани, ул. Анастасиевской, д.71");
        secondProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        secondProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        secondProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        secondProject.setStages(List.of(stageOneProjectTwo, stageTwoProjectTwo, stageThreeProjectTwo, stageFourProjectTwo));
        projectRepository.save(secondProject);

        // endregion PROJECT 2
        // region PROJECT 3

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectThree = new Person();
        foremanTaskStageOneOneProjectThree.setFirstName("Вадим");
        foremanTaskStageOneOneProjectThree.setSurname("Керунов");
        foremanTaskStageOneOneProjectThree.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectThree);

        final Task taskOneStageOneProjectThree = new Task();
        taskOneStageOneProjectThree.setName("Изучение грунта");
        taskOneStageOneProjectThree.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectThree.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectThree.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectThree.setHead(foremanTaskStageOneOneProjectThree);
        taskRepository.save(taskOneStageOneProjectThree);

        final Person foremanTaskTwoStageOneProjectThree = new Person();
        foremanTaskTwoStageOneProjectThree.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectThree.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectThree.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectThree);

        final Task taskTwoStageOneProjectThree = new Task();
        taskTwoStageOneProjectThree.setName("Расчистка территорий");
        taskTwoStageOneProjectThree.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectThree.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectThree.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectThree.setHead(foremanTaskTwoStageOneProjectThree);
        taskRepository.save(taskTwoStageOneProjectThree);

        final Person foremanTaskThreeStageOneProjectThree = new Person();
        foremanTaskThreeStageOneProjectThree.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectThree.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectThree.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectThree);

        final Task taskThreeStageOneProjectThree = new Task();
        taskThreeStageOneProjectThree.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectThree.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectThree.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectThree.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectThree.setHead(foremanTaskThreeStageOneProjectThree);
        taskRepository.save(taskThreeStageOneProjectThree);

        final Person foremanTaskFourStageProjectThree = new Person();
        foremanTaskFourStageProjectThree.setFirstName("Вениамин");
        foremanTaskFourStageProjectThree.setSurname("Немо");
        foremanTaskFourStageProjectThree.setFatherName("Игнатьевич");
        foremanTaskFourStageProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageProjectThree);

        final Task taskFourStageOneProjectThree = new Task();
        taskFourStageOneProjectThree.setName("Планирование зон");
        taskFourStageOneProjectThree.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectThree.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectThree.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectThree.setHead(foremanTaskFourStageProjectThree);
        taskRepository.save(taskFourStageOneProjectThree);

        final Person foremanStageOneProjectThree = new Person();
        foremanStageOneProjectThree.setFirstName("Илларион");
        foremanStageOneProjectThree.setSurname("Веселов");
        foremanStageOneProjectThree.setFatherName("Алексеевич");
        foremanStageOneProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectThree);

        final Stage stageOneProjectThree = new Stage();
        stageOneProjectThree.setName("Подготовительный этап");
        stageOneProjectThree.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectThree.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectThree.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectThree.setTasks(List.of(taskOneStageOneProjectThree, taskTwoStageOneProjectThree, taskThreeStageOneProjectThree, taskFourStageOneProjectThree));
        stageOneProjectThree.setHead(foremanStageOneProjectThree);
        stageRepository.save(stageOneProjectThree);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectThree = new Person();
        foremanTaskOneStageTwoProjectThree.setFirstName("Илья");
        foremanTaskOneStageTwoProjectThree.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectThree.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectThree);

        final Task taskOneStageTwoProjectThree = new Task();
        taskOneStageTwoProjectThree.setName("Изучение архивных материалов");
        taskOneStageTwoProjectThree.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectThree.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectThree.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectThree.setHead(foremanTaskOneStageTwoProjectThree);
        taskOneStageTwoProjectThree.setWorkers(List.of(foremanTaskOneStageTwoProjectThree));
        taskRepository.save(taskOneStageTwoProjectThree);

        final Person foremanTaskTwoStageTwoProjectThree = new Person();
        foremanTaskTwoStageTwoProjectThree.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectThree.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectThree.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectThree);

        final Task taskTwoStageTwoProjectThree = new Task();
        taskTwoStageTwoProjectThree.setName("Получение образцов пород");
        taskTwoStageTwoProjectThree.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectThree.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectThree.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectThree.setHead(foremanTaskTwoStageTwoProjectThree);
        taskTwoStageTwoProjectThree.setWorkers(List.of(foremanTaskTwoStageTwoProjectThree));
        taskRepository.save(taskTwoStageTwoProjectThree);


        final Person foremanTaskThreeStageTwoProjectThree = new Person();
        foremanTaskThreeStageTwoProjectThree.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectThree.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectThree.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectThree);

        final Task taskThreeStageTwoProjectThree = new Task();
        taskThreeStageTwoProjectThree.setName("Лабораторные исследования");
        taskThreeStageTwoProjectThree.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectThree.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectThree.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectThree.setHead(foremanTaskThreeStageTwoProjectThree);
        taskThreeStageTwoProjectThree.setWorkers(List.of(foremanTaskThreeStageTwoProjectThree));
        taskRepository.save(taskThreeStageTwoProjectThree);

        final Person foremanStageTwoProjectThree = new Person();
        foremanStageTwoProjectThree.setFirstName("Алексей");
        foremanStageTwoProjectThree.setSurname("Морозов");
        foremanStageTwoProjectThree.setFatherName("Евгеньевич");
        foremanStageTwoProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectThree);

        final Stage stageTwoProjectThree = new Stage();
        stageTwoProjectThree.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectThree.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectThree.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectThree.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectThree.setTasks(List.of(taskOneStageTwoProjectThree, taskTwoStageTwoProjectThree, taskThreeStageTwoProjectThree));
        stageTwoProjectThree.setHead(foremanStageTwoProjectThree);
        stageRepository.save(stageTwoProjectThree);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectThree = new Person();
        foremanTaskOneStageThreeProjectThree.setFirstName("Максим");
        foremanTaskOneStageThreeProjectThree.setSurname("Керунов");
        foremanTaskOneStageThreeProjectThree.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectThree);

        final Task taskOneStageThreeProjectThree = new Task();
        taskOneStageThreeProjectThree.setName("Составление задания на проектирование");
        taskOneStageThreeProjectThree.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectThree.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectThree.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectThree.setHead(foremanTaskOneStageThreeProjectThree);
        taskRepository.save(taskOneStageThreeProjectThree);

        final Person foremanTaskTwoStageThreeProjectThree = new Person();
        foremanTaskTwoStageThreeProjectThree.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectThree.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectThree.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectThree);

        final Task taskTwoStageThreeProjectThree = new Task();
        taskTwoStageThreeProjectThree.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectThree.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectThree.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectThree.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectThree.setHead(foremanTaskTwoStageThreeProjectThree);
        taskRepository.save(taskTwoStageThreeProjectThree);

        final Person foremanTaskThreeStageThreeProjectThree = new Person();
        foremanTaskThreeStageThreeProjectThree.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectThree.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectThree.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectThree);

        final Task taskThreeStageThreeProjectThree = new Task();
        taskThreeStageThreeProjectThree.setName("Разработка проектной документации");
        taskThreeStageThreeProjectThree.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectThree.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectThree.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectThree.setHead(foremanTaskThreeStageThreeProjectThree);
        taskRepository.save(taskThreeStageThreeProjectThree);

        final Person foremanTaskFourStageThreeProjectThree = new Person();
        foremanTaskFourStageThreeProjectThree.setFirstName("Максим");
        foremanTaskFourStageThreeProjectThree.setSurname("Кобол");
        foremanTaskFourStageThreeProjectThree.setFatherName("Артурович");
        foremanTaskFourStageThreeProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProjectThree);

        final Task taskFourStageThreeProjectThree = new Task();
        taskFourStageThreeProjectThree.setName("Разработка рабочей документации");
        taskFourStageThreeProjectThree.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectThree.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectThree.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectThree.setHead(foremanTaskFourStageThreeProjectThree);
        taskRepository.save(taskFourStageThreeProjectThree);

        final Person foremanTaskFiveStageProjectThree = new Person();
        foremanTaskFiveStageProjectThree.setFirstName("Александр");
        foremanTaskFiveStageProjectThree.setSurname("Чернышов");
        foremanTaskFiveStageProjectThree.setFatherName("Алексеевич");
        foremanTaskFiveStageProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageProjectThree);

        final Task taskFiveStageThreeProjectThree = new Task();
        taskFiveStageThreeProjectThree.setName("Экспертиза");
        taskFiveStageThreeProjectThree.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectThree.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectThree.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectThree.setHead(foremanTaskFiveStageProjectThree);
        taskRepository.save(taskFiveStageThreeProjectThree);

        final Person foremanTaskSixStageProjectThree = new Person();
        foremanTaskSixStageProjectThree.setFirstName("Артур");
        foremanTaskSixStageProjectThree.setSurname("Первышов");
        foremanTaskSixStageProjectThree.setFatherName("Маликович");
        foremanTaskSixStageProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageProjectThree);

        final Task taskSixStageThreeProjectThree = new Task();
        taskSixStageThreeProjectThree.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectThree.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectThree.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectThree.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectThree.setHead(foremanTaskSixStageProjectThree);
        taskRepository.save(taskSixStageThreeProjectThree);


        final Person foremanStageThreeProjectThree = new Person();
        foremanStageThreeProjectThree.setFirstName("Алексей");
        foremanStageThreeProjectThree.setSurname("Морозов");
        foremanStageThreeProjectThree.setFatherName("Евгеньевич");
        foremanStageThreeProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectThree);

        final Stage stageThreeProjectThree = new Stage();
        stageThreeProjectThree.setName("Проектирование здания");
        stageThreeProjectThree.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectThree.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectThree.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectThree.setTasks(List.of(taskOneStageThreeProjectThree, taskTwoStageThreeProjectThree, taskThreeStageThreeProjectThree, taskSixStageThreeProjectThree));
        stageThreeProjectThree.setHead(foremanStageThreeProjectThree);
        stageRepository.save(stageThreeProjectThree);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectThree = new Person();
        foremanTaskOneStageFourProjectThree.setFirstName("Вадим");
        foremanTaskOneStageFourProjectThree.setSurname("Владим");
        foremanTaskOneStageFourProjectThree.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectThree);

        final Task taskOneStageFourProjectThree = new Task();
        taskOneStageFourProjectThree.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectThree.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectThree.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectThree.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectThree.setHead(foremanTaskOneStageFourProjectThree);
        taskOneStageFourProjectThree.setWorkers(List.of(foremanTaskOneStageFourProjectThree));
        taskRepository.save(taskOneStageFourProjectThree);

        final Person foremanTaskTwoStageFourProjectThree = new Person();
        foremanTaskTwoStageFourProjectThree.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectThree.setSurname("Морозов");
        foremanTaskTwoStageFourProjectThree.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectThree);

        final Task taskTwoStageFourProjectThree = new Task();
        taskTwoStageFourProjectThree.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectThree.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectThree.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectThree.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectThree.setHead(foremanTaskTwoStageFourProjectThree);
        taskTwoStageFourProjectThree.setWorkers(List.of(foremanTaskTwoStageFourProjectThree));
        taskRepository.save(taskTwoStageFourProjectThree);

        final Person foremanTaskThreeStageFourProjectThree = new Person();
        foremanTaskThreeStageFourProjectThree.setFirstName("Петр");
        foremanTaskThreeStageFourProjectThree.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectThree.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectThree);

        final Task taskThreeStageFourProjectThree = new Task();
        taskThreeStageFourProjectThree.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectThree.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectThree.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectThree.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectThree.setHead(foremanTaskThreeStageFourProjectThree);
        taskThreeStageFourProjectThree.setWorkers(List.of(foremanTaskThreeStageFourProjectThree));
        taskRepository.save(taskThreeStageFourProjectThree);

        final Person foremanTaskFourStageFourProjectThree = new Person();
        foremanTaskFourStageFourProjectThree.setFirstName("Захар");
        foremanTaskFourStageFourProjectThree.setSurname("Бурунов");
        foremanTaskFourStageFourProjectThree.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectThree);

        final Task taskFourStageFourProjectThree = new Task();
        taskFourStageFourProjectThree.setName("Строительство крыши и навесов");
        taskFourStageFourProjectThree.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectThree.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectThree.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectThree.setHead(foremanTaskFourStageFourProjectThree);
        taskFourStageFourProjectThree.setWorkers(List.of(foremanTaskFourStageFourProjectThree));
        taskRepository.save(taskFourStageFourProjectThree);

        final Person foremanTaskFiveStageFourProjectThree = new Person();
        foremanTaskFiveStageFourProjectThree.setFirstName("Влад");
        foremanTaskFiveStageFourProjectThree.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectThree.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectThree);

        final Task taskFiveStageFourProjectThree = new Task();
        taskFiveStageFourProjectThree.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectThree.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectThree.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectThree.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectThree.setHead(foremanTaskFiveStageFourProjectThree);
        taskFiveStageFourProjectThree.setWorkers(List.of(foremanTaskFiveStageFourProjectThree));
        taskRepository.save(taskFiveStageFourProjectThree);

        final Person foremanTaskSixStageFourProjectThree = new Person();
        foremanTaskSixStageFourProjectThree.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectThree.setSurname("Носов");
        foremanTaskSixStageFourProjectThree.setFatherName("Олегович");
        foremanTaskSixStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectThree);

        final Task taskSixStageFourProjectThree = new Task();
        taskSixStageFourProjectThree.setName("Проведение коммуникаций");
        taskSixStageFourProjectThree.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectThree.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectThree.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectThree.setHead(foremanTaskSixStageFourProjectThree);
        taskSixStageFourProjectThree.setWorkers(List.of(foremanTaskSixStageFourProjectThree));
        taskRepository.save(taskSixStageFourProjectThree);

        final Person foremanTaskSevenStageFourProjectThree = new Person();
        foremanTaskSevenStageFourProjectThree.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectThree.setSurname("Морозов");
        foremanTaskSevenStageFourProjectThree.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectThree);

        final Task taskSevenStageFourProjectThree = new Task();
        taskSevenStageFourProjectThree.setName("Внутренняя отделка");
        taskSevenStageFourProjectThree.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectThree.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectThree.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectThree.setHead(foremanTaskSevenStageFourProjectThree);
        taskRepository.save(taskSevenStageFourProjectThree);

        final Person foremanTaskEightStageFourProjectThree = new Person();
        foremanTaskEightStageFourProjectThree.setFirstName("Иван");
        foremanTaskEightStageFourProjectThree.setSurname("Мегчов");
        foremanTaskEightStageFourProjectThree.setFatherName("Петрович");
        foremanTaskEightStageFourProjectThree.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectThree);

        final Task taskEightStageFourProjectThree = new Task();
        taskEightStageFourProjectThree.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectThree.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectThree.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectThree.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectThree.setHead(foremanTaskEightStageFourProjectThree);
        taskRepository.save(taskEightStageFourProjectThree);

        final Stage stageFourProjectThree = new Stage();
        stageFourProjectThree.setName("Возведение здания");
        stageFourProjectThree.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectThree.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectThree.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectThree.setTasks(List.of(taskOneStageFourProjectThree, taskTwoStageFourProjectThree, taskFiveStageFourProjectThree, taskEightStageFourProjectThree));
        stageFourProjectThree.setHead(foremanTaskEightStageFourProjectThree);
        stageRepository.save(stageFourProjectThree);

        // endregion STAGE 4

        final Project thirdProject = new Project();
        thirdProject.setName("Строительство многоквартирного дома в г.Сочи, ул. Санаторной, д.85");
        thirdProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        thirdProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        thirdProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        thirdProject.setStages(List.of(stageOneProjectThree, stageTwoProjectThree, stageThreeProjectThree, stageFourProjectThree));
        projectRepository.save(thirdProject);

        // endregion PROJECT 3
        // region PROJECT 4

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectFour = new Person();
        foremanTaskStageOneOneProjectFour.setFirstName("Вадим");
        foremanTaskStageOneOneProjectFour.setSurname("Керунов");
        foremanTaskStageOneOneProjectFour.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectFour);

        final Task taskOneStageOneProjectFour = new Task();
        taskOneStageOneProjectFour.setName("Изучение грунта");
        taskOneStageOneProjectFour.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectFour.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectFour.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectFour.setHead(foremanTaskStageOneOneProjectFour);
        taskRepository.save(taskOneStageOneProjectFour);

        final Person foremanTaskTwoStageOneProjectFour = new Person();
        foremanTaskTwoStageOneProjectFour.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectFour.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectFour.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectFour);

        final Task taskTwoStageOneProjectFour = new Task();
        taskTwoStageOneProjectFour.setName("Расчистка территорий");
        taskTwoStageOneProjectFour.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectFour.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectFour.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectFour.setHead(foremanTaskTwoStageOneProjectFour);
        taskRepository.save(taskTwoStageOneProjectFour);

        final Person foremanTaskThreeStageOneProjectFour = new Person();
        foremanTaskThreeStageOneProjectFour.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectFour.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectFour.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectFour);

        final Task taskThreeStageOneProjectFour = new Task();
        taskThreeStageOneProjectFour.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectFour.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectFour.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectFour.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectFour.setHead(foremanTaskThreeStageOneProjectFour);
        taskRepository.save(taskThreeStageOneProjectFour);

        final Person foremanTaskFourStageProjectFour = new Person();
        foremanTaskFourStageProjectFour.setFirstName("Вениамин");
        foremanTaskFourStageProjectFour.setSurname("Немо");
        foremanTaskFourStageProjectFour.setFatherName("Игнатьевич");
        foremanTaskFourStageProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageProjectFour);

        final Task taskFourStageOneProjectFour = new Task();
        taskFourStageOneProjectFour.setName("Планирование зон");
        taskFourStageOneProjectFour.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectFour.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectFour.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectFour.setHead(foremanTaskFourStageProjectFour);
        taskRepository.save(taskFourStageOneProjectFour);

        final Person foremanStageOneProjectFour = new Person();
        foremanStageOneProjectFour.setFirstName("Илларион");
        foremanStageOneProjectFour.setSurname("Веселов");
        foremanStageOneProjectFour.setFatherName("Алексеевич");
        foremanStageOneProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectFour);

        final Stage stageOneProjectFour = new Stage();
        stageOneProjectFour.setName("Подготовительный этап");
        stageOneProjectFour.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectFour.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectFour.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectFour.setTasks(List.of(taskOneStageOneProjectFour, taskTwoStageOneProjectFour, taskThreeStageOneProjectFour, taskFourStageOneProjectFour));
        stageOneProjectFour.setHead(foremanStageOneProjectFour);
        stageRepository.save(stageOneProjectFour);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectFour = new Person();
        foremanTaskOneStageTwoProjectFour.setFirstName("Илья");
        foremanTaskOneStageTwoProjectFour.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectFour.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectFour);

        final Task taskOneStageTwoProjectFour = new Task();
        taskOneStageTwoProjectFour.setName("Изучение архивных материалов");
        taskOneStageTwoProjectFour.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectFour.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectFour.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectFour.setHead(foremanTaskOneStageTwoProjectFour);
        taskOneStageTwoProjectFour.setWorkers(List.of(foremanTaskOneStageTwoProjectFour));
        taskRepository.save(taskOneStageTwoProjectFour);

        final Person foremanTaskTwoStageTwoProjectFour = new Person();
        foremanTaskTwoStageTwoProjectFour.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectFour.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectFour.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectFour);

        final Task taskTwoStageTwoProjectFour = new Task();
        taskTwoStageTwoProjectFour.setName("Получение образцов пород");
        taskTwoStageTwoProjectFour.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectFour.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectFour.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectFour.setHead(foremanTaskTwoStageTwoProjectFour);
        taskTwoStageTwoProjectFour.setWorkers(List.of(foremanTaskTwoStageTwoProjectFour));
        taskRepository.save(taskTwoStageTwoProjectFour);


        final Person foremanTaskThreeStageTwoProjectFour = new Person();
        foremanTaskThreeStageTwoProjectFour.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectFour.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectFour.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectFour);

        final Task taskThreeStageTwoProjectFour = new Task();
        taskThreeStageTwoProjectFour.setName("Лабораторные исследования");
        taskThreeStageTwoProjectFour.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectFour.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectFour.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectFour.setHead(foremanTaskThreeStageTwoProjectFour);
        taskThreeStageTwoProjectFour.setWorkers(List.of(foremanTaskThreeStageTwoProjectFour));
        taskRepository.save(taskThreeStageTwoProjectFour);

        final Person foremanStageTwoProjectFour = new Person();
        foremanStageTwoProjectFour.setFirstName("Алексей");
        foremanStageTwoProjectFour.setSurname("Морозов");
        foremanStageTwoProjectFour.setFatherName("Евгеньевич");
        foremanStageTwoProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectFour);

        final Stage stageTwoProjectFour = new Stage();
        stageTwoProjectFour.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectFour.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectFour.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectFour.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectFour.setTasks(List.of(taskOneStageTwoProjectFour, taskTwoStageTwoProjectFour, taskThreeStageTwoProjectFour));
        stageTwoProjectFour.setHead(foremanStageTwoProjectFour);
        stageRepository.save(stageTwoProjectFour);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectFour = new Person();
        foremanTaskOneStageThreeProjectFour.setFirstName("Максим");
        foremanTaskOneStageThreeProjectFour.setSurname("Керунов");
        foremanTaskOneStageThreeProjectFour.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectFour);

        final Task taskOneStageThreeProjectFour = new Task();
        taskOneStageThreeProjectFour.setName("Составление задания на проектирование");
        taskOneStageThreeProjectFour.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectFour.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectFour.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectFour.setHead(foremanTaskOneStageThreeProjectFour);
        taskRepository.save(taskOneStageThreeProjectFour);

        final Person foremanTaskTwoStageThreeProjectFour = new Person();
        foremanTaskTwoStageThreeProjectFour.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectFour.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectFour.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectFour);

        final Task taskTwoStageThreeProjectFour = new Task();
        taskTwoStageThreeProjectFour.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectFour.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectFour.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectFour.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectFour.setHead(foremanTaskTwoStageThreeProjectFour);
        taskRepository.save(taskTwoStageThreeProjectFour);

        final Person foremanTaskThreeStageThreeProjectFour = new Person();
        foremanTaskThreeStageThreeProjectFour.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectFour.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectFour.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectFour);

        final Task taskThreeStageThreeProjectFour = new Task();
        taskThreeStageThreeProjectFour.setName("Разработка проектной документации");
        taskThreeStageThreeProjectFour.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectFour.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectFour.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectFour.setHead(foremanTaskThreeStageThreeProjectFour);
        taskRepository.save(taskThreeStageThreeProjectFour);

        final Person foremanTaskFourStageThreeProjectFour = new Person();
        foremanTaskFourStageThreeProjectFour.setFirstName("Максим");
        foremanTaskFourStageThreeProjectFour.setSurname("Кобол");
        foremanTaskFourStageThreeProjectFour.setFatherName("Артурович");
        foremanTaskFourStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProjectFour);

        final Task taskFourStageThreeProjectFour = new Task();
        taskFourStageThreeProjectFour.setName("Разработка рабочей документации");
        taskFourStageThreeProjectFour.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectFour.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectFour.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectFour.setHead(foremanTaskFourStageThreeProjectFour);
        taskRepository.save(taskFourStageThreeProjectFour);

        final Person foremanTaskFiveStageProjectFour = new Person();
        foremanTaskFiveStageProjectFour.setFirstName("Александр");
        foremanTaskFiveStageProjectFour.setSurname("Чернышов");
        foremanTaskFiveStageProjectFour.setFatherName("Алексеевич");
        foremanTaskFiveStageProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageProjectFour);

        final Task taskFiveStageThreeProjectFour = new Task();
        taskFiveStageThreeProjectFour.setName("Экспертиза");
        taskFiveStageThreeProjectFour.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectFour.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectFour.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectFour.setHead(foremanTaskFiveStageProjectFour);
        taskRepository.save(taskFiveStageThreeProjectFour);

        final Person foremanTaskSixStageThreeProjectFour = new Person();
        foremanTaskSixStageThreeProjectFour.setFirstName("Артур");
        foremanTaskSixStageThreeProjectFour.setSurname("Первышов");
        foremanTaskSixStageThreeProjectFour.setFatherName("Маликович");
        foremanTaskSixStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageThreeProjectFour);

        final Task taskSixStageThreeProjectFour = new Task();
        taskSixStageThreeProjectFour.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectFour.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectFour.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectFour.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectFour.setHead(foremanTaskSixStageThreeProjectFour);
        taskRepository.save(taskSixStageThreeProjectFour);


        final Person foremanStageThreeProjectFour = new Person();
        foremanStageThreeProjectFour.setFirstName("Алексей");
        foremanStageThreeProjectFour.setSurname("Морозов");
        foremanStageThreeProjectFour.setFatherName("Евгеньевич");
        foremanStageThreeProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectFour);

        final Stage stageThreeProjectFour = new Stage();
        stageThreeProjectFour.setName("Проектирование здания");
        stageThreeProjectFour.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectFour.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectFour.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectFour.setTasks(List.of(taskOneStageThreeProjectFour, taskTwoStageThreeProjectFour, taskThreeStageThreeProjectFour, taskSixStageThreeProjectFour));
        stageThreeProjectFour.setHead(foremanStageThreeProjectFour);
        stageRepository.save(stageThreeProjectFour);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectFour = new Person();
        foremanTaskOneStageFourProjectFour.setFirstName("Вадим");
        foremanTaskOneStageFourProjectFour.setSurname("Владим");
        foremanTaskOneStageFourProjectFour.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectFour);

        final Task taskOneStageFourProjectFour = new Task();
        taskOneStageFourProjectFour.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectFour.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectFour.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectFour.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectFour.setHead(foremanTaskOneStageFourProjectFour);
        taskOneStageFourProjectFour.setWorkers(List.of(foremanTaskOneStageFourProjectFour));
        taskRepository.save(taskOneStageFourProjectFour);

        final Person foremanTaskTwoStageFourProjectFour = new Person();
        foremanTaskTwoStageFourProjectFour.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectFour.setSurname("Морозов");
        foremanTaskTwoStageFourProjectFour.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectFour);

        final Task taskTwoStageFourProjectFour = new Task();
        taskTwoStageFourProjectFour.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectFour.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectFour.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectFour.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectFour.setHead(foremanTaskTwoStageFourProjectFour);
        taskTwoStageFourProjectFour.setWorkers(List.of(foremanTaskTwoStageFourProjectFour));
        taskRepository.save(taskTwoStageFourProjectFour);

        final Person foremanTaskThreeStageFourProjectFour = new Person();
        foremanTaskThreeStageFourProjectFour.setFirstName("Петр");
        foremanTaskThreeStageFourProjectFour.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectFour.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectFour);

        final Task taskThreeStageFourProjectFour = new Task();
        taskThreeStageFourProjectFour.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectFour.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectFour.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectFour.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectFour.setHead(foremanTaskThreeStageFourProjectFour);
        taskThreeStageFourProjectFour.setWorkers(List.of(foremanTaskThreeStageFourProjectFour));
        taskRepository.save(taskThreeStageFourProjectFour);

        final Person foremanTaskFourStageFourProjectFour = new Person();
        foremanTaskFourStageFourProjectFour.setFirstName("Захар");
        foremanTaskFourStageFourProjectFour.setSurname("Бурунов");
        foremanTaskFourStageFourProjectFour.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectFour);

        final Task taskFourStageFourProjectFour = new Task();
        taskFourStageFourProjectFour.setName("Строительство крыши и навесов");
        taskFourStageFourProjectFour.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectFour.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectFour.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectFour.setHead(foremanTaskFourStageFourProjectFour);
        taskFourStageFourProjectFour.setWorkers(List.of(foremanTaskFourStageFourProjectFour));
        taskRepository.save(taskFourStageFourProjectFour);

        final Person foremanTaskFiveStageFourProjectFour = new Person();
        foremanTaskFiveStageFourProjectFour.setFirstName("Влад");
        foremanTaskFiveStageFourProjectFour.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectFour.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectFour);

        final Task taskFiveStageFourProjectFour = new Task();
        taskFiveStageFourProjectFour.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectFour.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectFour.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectFour.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectFour.setHead(foremanTaskFiveStageFourProjectFour);
        taskFiveStageFourProjectFour.setWorkers(List.of(foremanTaskFiveStageFourProjectFour));
        taskRepository.save(taskFiveStageFourProjectFour);

        final Person foremanTaskSixStageFourProjectFour = new Person();
        foremanTaskSixStageFourProjectFour.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectFour.setSurname("Носов");
        foremanTaskSixStageFourProjectFour.setFatherName("Олегович");
        foremanTaskSixStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectFour);

        final Task taskSixStageFourProjectFour = new Task();
        taskSixStageFourProjectFour.setName("Проведение коммуникаций");
        taskSixStageFourProjectFour.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectFour.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectFour.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectFour.setHead(foremanTaskSixStageFourProjectFour);
        taskSixStageFourProjectFour.setWorkers(List.of(foremanTaskSixStageFourProjectFour));
        taskRepository.save(taskSixStageFourProjectFour);

        final Person foremanTaskSevenStageFourProjectFour = new Person();
        foremanTaskSevenStageFourProjectFour.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectFour.setSurname("Морозов");
        foremanTaskSevenStageFourProjectFour.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectFour);

        final Task taskSevenStageFourProjectFour = new Task();
        taskSevenStageFourProjectFour.setName("Внутренняя отделка");
        taskSevenStageFourProjectFour.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectFour.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectFour.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectFour.setHead(foremanTaskSevenStageFourProjectFour);
        taskRepository.save(taskSevenStageFourProjectFour);

        final Person foremanTaskEightStageFourProjectFour = new Person();
        foremanTaskEightStageFourProjectFour.setFirstName("Иван");
        foremanTaskEightStageFourProjectFour.setSurname("Мегчов");
        foremanTaskEightStageFourProjectFour.setFatherName("Петрович");
        foremanTaskEightStageFourProjectFour.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectFour);

        final Task taskEightStageFourProjectFour = new Task();
        taskEightStageFourProjectFour.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectFour.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectFour.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectFour.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectFour.setHead(foremanTaskEightStageFourProjectFour);
        taskRepository.save(taskEightStageFourProjectFour);

        final Stage stageFourProjectFour = new Stage();
        stageFourProjectFour.setName("Возведение здания");
        stageFourProjectFour.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectFour.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectFour.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectFour.setTasks(List.of(taskOneStageFourProjectFour, taskTwoStageFourProjectFour, taskFiveStageFourProjectFour, taskEightStageFourProjectFour));
        stageFourProjectFour.setHead(foremanTaskEightStageFourProjectFour);
        stageRepository.save(stageFourProjectFour);

        // endregion STAGE 4

        final Project fourthProject = new Project();
        fourthProject.setName("Строительство многоквартирного дома в г.Краснодар, ул. Медовая, д.9");
        fourthProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        fourthProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        fourthProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        fourthProject.setStages(List.of(stageOneProjectFour, stageTwoProjectFour, stageThreeProjectFour, stageFourProjectFour));
        projectRepository.save(fourthProject);


        // endregion PROJECT 4
        // region PROJECT 5

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectFive = new Person();
        foremanTaskStageOneOneProjectFive.setFirstName("Вадим");
        foremanTaskStageOneOneProjectFive.setSurname("Керунов");
        foremanTaskStageOneOneProjectFive.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectFive);

        final Task taskOneStageOneProjectFive = new Task();
        taskOneStageOneProjectFive.setName("Изучение грунта");
        taskOneStageOneProjectFive.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectFive.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectFive.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectFive.setHead(foremanTaskStageOneOneProjectFive);
        taskRepository.save(taskOneStageOneProjectFive);

        final Person foremanTaskTwoStageOneProjectFive = new Person();
        foremanTaskTwoStageOneProjectFive.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectFive.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectFive.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectFive);

        final Task taskTwoStageOneProjectFive = new Task();
        taskTwoStageOneProjectFive.setName("Расчистка территорий");
        taskTwoStageOneProjectFive.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectFive.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectFive.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectFive.setHead(foremanTaskTwoStageOneProjectFive);
        taskRepository.save(taskTwoStageOneProjectFive);

        final Person foremanTaskThreeStageOneProjectFive = new Person();
        foremanTaskThreeStageOneProjectFive.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectFive.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectFive.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectFive);

        final Task taskThreeStageOneProjectFive = new Task();
        taskThreeStageOneProjectFive.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectFive.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectFive.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectFive.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectFive.setHead(foremanTaskThreeStageOneProjectFive);
        taskRepository.save(taskThreeStageOneProjectFive);

        final Person foremanTaskFourStageProjectFive = new Person();
        foremanTaskFourStageProjectFive.setFirstName("Вениамин");
        foremanTaskFourStageProjectFive.setSurname("Немо");
        foremanTaskFourStageProjectFive.setFatherName("Игнатьевич");
        foremanTaskFourStageProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageProjectFive);

        final Task taskFourStageOneProjectFive = new Task();
        taskFourStageOneProjectFive.setName("Планирование зон");
        taskFourStageOneProjectFive.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectFive.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectFive.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectFive.setHead(foremanTaskFourStageProjectFive);
        taskRepository.save(taskFourStageOneProjectFive);

        final Person foremanStageOneProjectFive = new Person();
        foremanStageOneProjectFive.setFirstName("Илларион");
        foremanStageOneProjectFive.setSurname("Веселов");
        foremanStageOneProjectFive.setFatherName("Алексеевич");
        foremanStageOneProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectFive);

        final Stage stageOneProjectFive = new Stage();
        stageOneProjectFive.setName("Подготовительный этап");
        stageOneProjectFive.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectFive.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectFive.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectFive.setTasks(List.of(taskOneStageOneProjectFive, taskTwoStageOneProjectFive, taskThreeStageOneProjectFive, taskFourStageOneProjectFive));
        stageOneProjectFive.setHead(foremanStageOneProjectFive);
        stageRepository.save(stageOneProjectFive);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectFive = new Person();
        foremanTaskOneStageTwoProjectFive.setFirstName("Илья");
        foremanTaskOneStageTwoProjectFive.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectFive.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectFive);

        final Task taskOneStageTwoProjectFive = new Task();
        taskOneStageTwoProjectFive.setName("Изучение архивных материалов");
        taskOneStageTwoProjectFive.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectFive.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectFive.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectFive.setHead(foremanTaskOneStageTwoProjectFive);
        taskOneStageTwoProjectFive.setWorkers(List.of(foremanTaskOneStageTwoProjectFive));
        taskRepository.save(taskOneStageTwoProjectFive);

        final Person foremanTaskTwoStageTwoProjectFive = new Person();
        foremanTaskTwoStageTwoProjectFive.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectFive.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectFive.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectFive);

        final Task taskTwoStageTwoProjectFive = new Task();
        taskTwoStageTwoProjectFive.setName("Получение образцов пород");
        taskTwoStageTwoProjectFive.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectFive.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectFive.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectFive.setHead(foremanTaskTwoStageTwoProjectFive);
        taskTwoStageTwoProjectFive.setWorkers(List.of(foremanTaskTwoStageTwoProjectFive));
        taskRepository.save(taskTwoStageTwoProjectFive);


        final Person foremanTaskThreeStageTwoProjectFive = new Person();
        foremanTaskThreeStageTwoProjectFive.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectFive.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectFive.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectFive);

        final Task taskThreeStageTwoProjectFive = new Task();
        taskThreeStageTwoProjectFive.setName("Лабораторные исследования");
        taskThreeStageTwoProjectFive.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectFive.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectFive.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectFive.setHead(foremanTaskThreeStageTwoProjectFive);
        taskThreeStageTwoProjectFive.setWorkers(List.of(foremanTaskThreeStageTwoProjectFive));
        taskRepository.save(taskThreeStageTwoProjectFive);

        final Person foremanStageTwoProjectFive = new Person();
        foremanStageTwoProjectFive.setFirstName("Алексей");
        foremanStageTwoProjectFive.setSurname("Морозов");
        foremanStageTwoProjectFive.setFatherName("Евгеньевич");
        foremanStageTwoProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectFive);

        final Stage stageTwoProjectFive = new Stage();
        stageTwoProjectFive.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectFive.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectFive.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectFive.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectFive.setTasks(List.of(taskOneStageTwoProjectFive, taskTwoStageTwoProjectFive, taskThreeStageTwoProjectFive));
        stageTwoProjectFive.setHead(foremanStageTwoProjectFive);
        stageRepository.save(stageTwoProjectFive);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectFive = new Person();
        foremanTaskOneStageThreeProjectFive.setFirstName("Максим");
        foremanTaskOneStageThreeProjectFive.setSurname("Керунов");
        foremanTaskOneStageThreeProjectFive.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectFive);

        final Task taskOneStageThreeProjectFive = new Task();
        taskOneStageThreeProjectFive.setName("Составление задания на проектирование");
        taskOneStageThreeProjectFive.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectFive.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectFive.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectFive.setHead(foremanTaskOneStageThreeProjectFive);
        taskRepository.save(taskOneStageThreeProjectFive);

        final Person foremanTaskTwoStageThreeProjectFive = new Person();
        foremanTaskTwoStageThreeProjectFive.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectFive.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectFive.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectFive);

        final Task taskTwoStageThreeProjectFive = new Task();
        taskTwoStageThreeProjectFive.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectFive.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectFive.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectFive.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectFive.setHead(foremanTaskTwoStageThreeProjectFive);
        taskRepository.save(taskTwoStageThreeProjectFive);

        final Person foremanTaskThreeStageThreeProjectFive = new Person();
        foremanTaskThreeStageThreeProjectFive.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectFive.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectFive.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectFive);

        final Task taskThreeStageThreeProjectFive = new Task();
        taskThreeStageThreeProjectFive.setName("Разработка проектной документации");
        taskThreeStageThreeProjectFive.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectFive.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectFive.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectFive.setHead(foremanTaskThreeStageThreeProjectFive);
        taskRepository.save(taskThreeStageThreeProjectFive);

        final Person foremanTaskFourStageThreeProjectFive = new Person();
        foremanTaskFourStageThreeProjectFive.setFirstName("Максим");
        foremanTaskFourStageThreeProjectFive.setSurname("Кобол");
        foremanTaskFourStageThreeProjectFive.setFatherName("Артурович");
        foremanTaskFourStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProjectFive);

        final Task taskFourStageThreeProjectFive = new Task();
        taskFourStageThreeProjectFive.setName("Разработка рабочей документации");
        taskFourStageThreeProjectFive.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectFive.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectFive.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectFive.setHead(foremanTaskFourStageThreeProjectFive);
        taskRepository.save(taskFourStageThreeProjectFive);

        final Person foremanTaskFiveStageProjectFive = new Person();
        foremanTaskFiveStageProjectFive.setFirstName("Александр");
        foremanTaskFiveStageProjectFive.setSurname("Чернышов");
        foremanTaskFiveStageProjectFive.setFatherName("Алексеевич");
        foremanTaskFiveStageProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageProjectFive);

        final Task taskFiveStageThreeProjectFive = new Task();
        taskFiveStageThreeProjectFive.setName("Экспертиза");
        taskFiveStageThreeProjectFive.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectFive.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectFive.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectFive.setHead(foremanTaskFiveStageProjectFive);
        taskRepository.save(taskFiveStageThreeProjectFive);

        final Person foremanTaskSixStageThreeProjectFive = new Person();
        foremanTaskSixStageThreeProjectFive.setFirstName("Артур");
        foremanTaskSixStageThreeProjectFive.setSurname("Первышов");
        foremanTaskSixStageThreeProjectFive.setFatherName("Маликович");
        foremanTaskSixStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageThreeProjectFive);

        final Task taskSixStageThreeProjectFive = new Task();
        taskSixStageThreeProjectFive.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectFive.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectFive.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectFive.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectFive.setHead(foremanTaskSixStageThreeProjectFive);
        taskRepository.save(taskSixStageThreeProjectFive);


        final Person foremanStageThreeProjectFive = new Person();
        foremanStageThreeProjectFive.setFirstName("Алексей");
        foremanStageThreeProjectFive.setSurname("Морозов");
        foremanStageThreeProjectFive.setFatherName("Евгеньевич");
        foremanStageThreeProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectFive);

        final Stage stageThreeProjectFive = new Stage();
        stageThreeProjectFive.setName("Проектирование здания");
        stageThreeProjectFive.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectFive.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectFive.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectFive.setTasks(List.of(taskOneStageThreeProjectFive, taskTwoStageThreeProjectFive, taskThreeStageThreeProjectFive, taskSixStageThreeProjectFive));
        stageThreeProjectFive.setHead(foremanStageThreeProjectFive);
        stageRepository.save(stageThreeProjectFive);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectFive = new Person();
        foremanTaskOneStageFourProjectFive.setFirstName("Вадим");
        foremanTaskOneStageFourProjectFive.setSurname("Владим");
        foremanTaskOneStageFourProjectFive.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectFive);

        final Task taskOneStageFourProjectFive = new Task();
        taskOneStageFourProjectFive.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectFive.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectFive.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectFive.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectFive.setHead(foremanTaskOneStageFourProjectFive);
        taskOneStageFourProjectFive.setWorkers(List.of(foremanTaskOneStageFourProjectFive));
        taskRepository.save(taskOneStageFourProjectFive);

        final Person foremanTaskTwoStageFourProjectFive = new Person();
        foremanTaskTwoStageFourProjectFive.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectFive.setSurname("Морозов");
        foremanTaskTwoStageFourProjectFive.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectFive);

        final Task taskTwoStageFourProjectFive = new Task();
        taskTwoStageFourProjectFive.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectFive.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectFive.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectFive.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectFive.setHead(foremanTaskTwoStageFourProjectFive);
        taskTwoStageFourProjectFive.setWorkers(List.of(foremanTaskTwoStageFourProjectFive));
        taskRepository.save(taskTwoStageFourProjectFive);

        final Person foremanTaskThreeStageFourProjectFive = new Person();
        foremanTaskThreeStageFourProjectFive.setFirstName("Петр");
        foremanTaskThreeStageFourProjectFive.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectFive.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectFive);

        final Task taskThreeStageFourProjectFive = new Task();
        taskThreeStageFourProjectFive.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectFive.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectFive.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectFive.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectFive.setHead(foremanTaskThreeStageFourProjectFive);
        taskThreeStageFourProjectFive.setWorkers(List.of(foremanTaskThreeStageFourProjectFive));
        taskRepository.save(taskThreeStageFourProjectFive);

        final Person foremanTaskFourStageFourProjectFive = new Person();
        foremanTaskFourStageFourProjectFive.setFirstName("Захар");
        foremanTaskFourStageFourProjectFive.setSurname("Бурунов");
        foremanTaskFourStageFourProjectFive.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectFive);

        final Task taskFourStageFourProjectFive = new Task();
        taskFourStageFourProjectFive.setName("Строительство крыши и навесов");
        taskFourStageFourProjectFive.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectFive.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectFive.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectFive.setHead(foremanTaskFourStageFourProjectFive);
        taskFourStageFourProjectFive.setWorkers(List.of(foremanTaskFourStageFourProjectFive));
        taskRepository.save(taskFourStageFourProjectFive);

        final Person foremanTaskFiveStageFourProjectFive = new Person();
        foremanTaskFiveStageFourProjectFive.setFirstName("Влад");
        foremanTaskFiveStageFourProjectFive.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectFive.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectFive);

        final Task taskFiveStageFourProjectFive = new Task();
        taskFiveStageFourProjectFive.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectFive.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectFive.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectFive.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectFive.setHead(foremanTaskFiveStageFourProjectFive);
        taskFiveStageFourProjectFive.setWorkers(List.of(foremanTaskFiveStageFourProjectFive));
        taskRepository.save(taskFiveStageFourProjectFive);

        final Person foremanTaskSixStageFourProjectFive = new Person();
        foremanTaskSixStageFourProjectFive.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectFive.setSurname("Носов");
        foremanTaskSixStageFourProjectFive.setFatherName("Олегович");
        foremanTaskSixStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectFive);

        final Task taskSixStageFourProjectFive = new Task();
        taskSixStageFourProjectFive.setName("Проведение коммуникаций");
        taskSixStageFourProjectFive.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectFive.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectFive.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectFive.setHead(foremanTaskSixStageFourProjectFive);
        taskSixStageFourProjectFive.setWorkers(List.of(foremanTaskSixStageFourProjectFive));
        taskRepository.save(taskSixStageFourProjectFive);

        final Person foremanTaskSevenStageFourProjectFive = new Person();
        foremanTaskSevenStageFourProjectFive.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectFive.setSurname("Морозов");
        foremanTaskSevenStageFourProjectFive.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectFive);

        final Task taskSevenStageFourProjectFive = new Task();
        taskSevenStageFourProjectFive.setName("Внутренняя отделка");
        taskSevenStageFourProjectFive.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectFive.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectFive.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectFive.setHead(foremanTaskSevenStageFourProjectFive);
        taskRepository.save(taskSevenStageFourProjectFive);

        final Person foremanTaskEightStageFourProjectFive = new Person();
        foremanTaskEightStageFourProjectFive.setFirstName("Иван");
        foremanTaskEightStageFourProjectFive.setSurname("Мегчов");
        foremanTaskEightStageFourProjectFive.setFatherName("Петрович");
        foremanTaskEightStageFourProjectFive.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectFive);

        final Task taskEightStageFourProjectFive = new Task();
        taskEightStageFourProjectFive.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectFive.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectFive.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectFive.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectFive.setHead(foremanTaskEightStageFourProjectFive);
        taskRepository.save(taskEightStageFourProjectFive);

        final Stage stageFourProjectFive = new Stage();
        stageFourProjectFive.setName("Возведение здания");
        stageFourProjectFive.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectFive.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectFive.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectFive.setTasks(List.of(taskOneStageFourProjectFive, taskTwoStageFourProjectFive, taskFiveStageFourProjectFive, taskEightStageFourProjectFive));
        stageFourProjectFive.setHead(foremanTaskEightStageFourProjectFive);
        stageRepository.save(stageFourProjectFive);

        // endregion STAGE 4

        final Project fifthProject = new Project();
        fifthProject.setName("Строительство многоквартирного дома в г.Cочи, ул. Дагомысская, д.19");
        fifthProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        fifthProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        fifthProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        fifthProject.setStages(List.of(stageOneProjectFive, stageTwoProjectFive, stageThreeProjectFive, stageFourProjectFive));
        projectRepository.save(fifthProject);


        // endregion PROJECT 5
        // region PROJECT 6

        // region STAGE 1

        final Person foremanTaskStageOneOneProjectSix = new Person();
        foremanTaskStageOneOneProjectSix.setFirstName("Вадим");
        foremanTaskStageOneOneProjectSix.setSurname("Керунов");
        foremanTaskStageOneOneProjectSix.setFatherName("Евгеньевич");
        foremanTaskStageOneOneProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskStageOneOneProjectSix);

        final Task taskOneStageOneProjectSix = new Task();
        taskOneStageOneProjectSix.setName("Изучение грунта");
        taskOneStageOneProjectSix.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        taskOneStageOneProjectSix.setActualEndDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskOneStageOneProjectSix.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskOneStageOneProjectSix.setHead(foremanTaskStageOneOneProjectSix);
        taskRepository.save(taskOneStageOneProjectSix);

        final Person foremanTaskTwoStageOneProjectSix = new Person();
        foremanTaskTwoStageOneProjectSix.setFirstName("Вадим");
        foremanTaskTwoStageOneProjectSix.setSurname("Ширяев");
        foremanTaskTwoStageOneProjectSix.setFatherName("Николаевич");
        foremanTaskTwoStageOneProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageOneProjectSix);

        final Task taskTwoStageOneProjectSix = new Task();
        taskTwoStageOneProjectSix.setName("Расчистка территорий");
        taskTwoStageOneProjectSix.setStartDate(Instant.parse("2019-05-27T00:00:00Z"));
        taskTwoStageOneProjectSix.setActualEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectSix.setExpectedEndDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskTwoStageOneProjectSix.setHead(foremanTaskTwoStageOneProjectSix);
        taskRepository.save(taskTwoStageOneProjectSix);

        final Person foremanTaskThreeStageOneProjectSix = new Person();
        foremanTaskThreeStageOneProjectSix.setFirstName("Вениамин");
        foremanTaskThreeStageOneProjectSix.setSurname("Давыдов");
        foremanTaskThreeStageOneProjectSix.setFatherName("Игнатьевич");
        foremanTaskThreeStageOneProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageOneProjectSix);

        final Task taskThreeStageOneProjectSix = new Task();
        taskThreeStageOneProjectSix.setName("Отвод грунтовых вод");
        taskThreeStageOneProjectSix.setStartDate(Instant.parse("2019-05-30T00:00:00Z"));
        taskThreeStageOneProjectSix.setActualEndDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskThreeStageOneProjectSix.setExpectedEndDate(Instant.parse("2019-06-16T00:00:00Z"));
        taskThreeStageOneProjectSix.setHead(foremanTaskThreeStageOneProjectSix);
        taskRepository.save(taskThreeStageOneProjectSix);

        final Person foremanTaskFourStageProjectSix = new Person();
        foremanTaskFourStageProjectSix.setFirstName("Вениамин");
        foremanTaskFourStageProjectSix.setSurname("Немо");
        foremanTaskFourStageProjectSix.setFatherName("Игнатьевич");
        foremanTaskFourStageProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageProjectSix);

        final Task taskFourStageOneProjectSix = new Task();
        taskFourStageOneProjectSix.setName("Планирование зон");
        taskFourStageOneProjectSix.setStartDate(Instant.parse("2019-06-15T00:00:00Z"));
        taskFourStageOneProjectSix.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        taskFourStageOneProjectSix.setExpectedEndDate(Instant.parse("2019-07-10T00:00:00Z"));
        taskFourStageOneProjectSix.setHead(foremanTaskFourStageProjectSix);
        taskRepository.save(taskFourStageOneProjectSix);

        final Person foremanStageOneProjectSix = new Person();
        foremanStageOneProjectSix.setFirstName("Илларион");
        foremanStageOneProjectSix.setSurname("Веселов");
        foremanStageOneProjectSix.setFatherName("Алексеевич");
        foremanStageOneProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanStageOneProjectSix);

        final Stage stageOneProjectSix = new Stage();
        stageOneProjectSix.setName("Подготовительный этап");
        stageOneProjectSix.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        stageOneProjectSix.setActualEndDate(Instant.parse("2019-07-09T00:00:00Z"));
        stageOneProjectSix.setExpectedEndDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageOneProjectSix.setTasks(List.of(taskOneStageOneProjectSix, taskTwoStageOneProjectSix, taskThreeStageOneProjectSix, taskFourStageOneProjectSix));
        stageOneProjectSix.setHead(foremanStageOneProjectSix);
        stageRepository.save(stageOneProjectSix);

        // endregion STAGE 1

        //region STAGE 2


        final Person foremanTaskOneStageTwoProjectSix = new Person();
        foremanTaskOneStageTwoProjectSix.setFirstName("Илья");
        foremanTaskOneStageTwoProjectSix.setSurname("Игнатов");
        foremanTaskOneStageTwoProjectSix.setFatherName("Александрович");
        foremanTaskOneStageTwoProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageTwoProjectSix);

        final Task taskOneStageTwoProjectSix = new Task();
        taskOneStageTwoProjectSix.setName("Изучение архивных материалов");
        taskOneStageTwoProjectSix.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        taskOneStageTwoProjectSix.setActualEndDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskOneStageTwoProjectSix.setExpectedEndDate(Instant.parse("2024-01-15T00:00:00Z"));
        taskOneStageTwoProjectSix.setHead(foremanTaskOneStageTwoProjectSix);
        taskOneStageTwoProjectSix.setWorkers(List.of(foremanTaskOneStageTwoProjectSix));
        taskRepository.save(taskOneStageTwoProjectSix);

        final Person foremanTaskTwoStageTwoProjectSix = new Person();
        foremanTaskTwoStageTwoProjectSix.setFirstName("Илларион");
        foremanTaskTwoStageTwoProjectSix.setSurname("Козлов");
        foremanTaskTwoStageTwoProjectSix.setFatherName("Максимович");
        foremanTaskTwoStageTwoProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageTwoProjectSix);

        final Task taskTwoStageTwoProjectSix = new Task();
        taskTwoStageTwoProjectSix.setName("Получение образцов пород");
        taskTwoStageTwoProjectSix.setStartDate(Instant.parse("2020-12-01T00:00:00Z"));
        taskTwoStageTwoProjectSix.setActualEndDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskTwoStageTwoProjectSix.setExpectedEndDate(Instant.parse("2021-05-30T00:00:00Z"));
        taskTwoStageTwoProjectSix.setHead(foremanTaskTwoStageTwoProjectSix);
        taskTwoStageTwoProjectSix.setWorkers(List.of(foremanTaskTwoStageTwoProjectSix));
        taskRepository.save(taskTwoStageTwoProjectSix);


        final Person foremanTaskThreeStageTwoProjectSix = new Person();
        foremanTaskThreeStageTwoProjectSix.setFirstName("Жанна");
        foremanTaskThreeStageTwoProjectSix.setSurname("Мирович");
        foremanTaskThreeStageTwoProjectSix.setFatherName("Андреевна");
        foremanTaskThreeStageTwoProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageTwoProjectSix);

        final Task taskThreeStageTwoProjectSix = new Task();
        taskThreeStageTwoProjectSix.setName("Лабораторные исследования");
        taskThreeStageTwoProjectSix.setStartDate(Instant.parse("2021-04-15T00:00:00Z"));
        taskThreeStageTwoProjectSix.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskThreeStageTwoProjectSix.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        taskThreeStageTwoProjectSix.setHead(foremanTaskThreeStageTwoProjectSix);
        taskThreeStageTwoProjectSix.setWorkers(List.of(foremanTaskThreeStageTwoProjectSix));
        taskRepository.save(taskThreeStageTwoProjectSix);

        final Person foremanStageTwoProjectSix = new Person();
        foremanStageTwoProjectSix.setFirstName("Алексей");
        foremanStageTwoProjectSix.setSurname("Морозов");
        foremanStageTwoProjectSix.setFatherName("Евгеньевич");
        foremanStageTwoProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanStageTwoProjectSix);

        final Stage stageTwoProjectSix = new Stage();
        stageTwoProjectSix.setName("Проведение инженерно-геологических изысканий");
        stageTwoProjectSix.setStartDate(Instant.parse("2019-08-24T00:00:00Z"));
        stageTwoProjectSix.setActualEndDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageTwoProjectSix.setExpectedEndDate(Instant.parse("2021-06-24T00:00:00Z"));
        stageTwoProjectSix.setTasks(List.of(taskOneStageTwoProjectSix, taskTwoStageTwoProjectSix, taskThreeStageTwoProjectSix));
        stageTwoProjectSix.setHead(foremanStageTwoProjectSix);
        stageRepository.save(stageTwoProjectSix);

        //endregion STAGE 2

        // region STAGE 3

        final Person foremanTaskOneStageThreeProjectSix = new Person();
        foremanTaskOneStageThreeProjectSix.setFirstName("Максим");
        foremanTaskOneStageThreeProjectSix.setSurname("Керунов");
        foremanTaskOneStageThreeProjectSix.setFatherName("Евгеньевич");
        foremanTaskOneStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageThreeProjectSix);

        final Task taskOneStageThreeProjectSix = new Task();
        taskOneStageThreeProjectSix.setName("Составление задания на проектирование");
        taskOneStageThreeProjectSix.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        taskOneStageThreeProjectSix.setActualEndDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskOneStageThreeProjectSix.setExpectedEndDate(Instant.parse("2021-09-30T00:00:00Z"));
        taskOneStageThreeProjectSix.setHead(foremanTaskOneStageThreeProjectSix);
        taskRepository.save(taskOneStageThreeProjectSix);

        final Person foremanTaskTwoStageThreeProjectSix = new Person();
        foremanTaskTwoStageThreeProjectSix.setFirstName("Максим");
        foremanTaskTwoStageThreeProjectSix.setSurname("Керунов");
        foremanTaskTwoStageThreeProjectSix.setFatherName("Евгеньевич");
        foremanTaskTwoStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageThreeProjectSix);

        final Task taskTwoStageThreeProjectSix = new Task();
        taskTwoStageThreeProjectSix.setName("Разработка основных технических решений");
        taskTwoStageThreeProjectSix.setStartDate(Instant.parse("2021-10-01T00:00:00Z"));
        taskTwoStageThreeProjectSix.setActualEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectSix.setExpectedEndDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskTwoStageThreeProjectSix.setHead(foremanTaskTwoStageThreeProjectSix);
        taskRepository.save(taskTwoStageThreeProjectSix);

        final Person foremanTaskThreeStageThreeProjectSix = new Person();
        foremanTaskThreeStageThreeProjectSix.setFirstName("Максим");
        foremanTaskThreeStageThreeProjectSix.setSurname("Керунов");
        foremanTaskThreeStageThreeProjectSix.setFatherName("Евгеньевич");
        foremanTaskThreeStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageThreeProjectSix);

        final Task taskThreeStageThreeProjectSix = new Task();
        taskThreeStageThreeProjectSix.setName("Разработка проектной документации");
        taskThreeStageThreeProjectSix.setStartDate(Instant.parse("2021-12-27T00:00:00Z"));
        taskThreeStageThreeProjectSix.setActualEndDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskThreeStageThreeProjectSix.setExpectedEndDate(Instant.parse("2022-02-25T00:00:00Z"));
        taskThreeStageThreeProjectSix.setHead(foremanTaskThreeStageThreeProjectSix);
        taskRepository.save(taskThreeStageThreeProjectSix);

        final Person foremanTaskFourStageThreeProjectSix = new Person();
        foremanTaskFourStageThreeProjectSix.setFirstName("Максим");
        foremanTaskFourStageThreeProjectSix.setSurname("Кобол");
        foremanTaskFourStageThreeProjectSix.setFatherName("Артурович");
        foremanTaskFourStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageThreeProjectSix);

        final Task taskFourStageThreeProjectSix = new Task();
        taskFourStageThreeProjectSix.setName("Разработка рабочей документации");
        taskFourStageThreeProjectSix.setStartDate(Instant.parse("2022-01-27T00:00:00Z"));
        taskFourStageThreeProjectSix.setActualEndDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFourStageThreeProjectSix.setExpectedEndDate(Instant.parse("2022-04-30T00:00:00Z"));
        taskFourStageThreeProjectSix.setHead(foremanTaskFourStageThreeProjectSix);
        taskRepository.save(taskFourStageThreeProjectSix);

        final Person foremanTaskFiveStageProjectSix = new Person();
        foremanTaskFiveStageProjectSix.setFirstName("Александр");
        foremanTaskFiveStageProjectSix.setSurname("Чернышов");
        foremanTaskFiveStageProjectSix.setFatherName("Алексеевич");
        foremanTaskFiveStageProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageProjectSix);

        final Task taskFiveStageThreeProjectSix = new Task();
        taskFiveStageThreeProjectSix.setName("Экспертиза");
        taskFiveStageThreeProjectSix.setStartDate(Instant.parse("2022-04-10T00:00:00Z"));
        taskFiveStageThreeProjectSix.setActualEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectSix.setExpectedEndDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskFiveStageThreeProjectSix.setHead(foremanTaskFiveStageProjectSix);
        taskRepository.save(taskFiveStageThreeProjectSix);

        final Person foremanTaskSixStageThreeProjectSix = new Person();
        foremanTaskSixStageThreeProjectSix.setFirstName("Артур");
        foremanTaskSixStageThreeProjectSix.setSurname("Первышов");
        foremanTaskSixStageThreeProjectSix.setFatherName("Маликович");
        foremanTaskSixStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageThreeProjectSix);

        final Task taskSixStageThreeProjectSix = new Task();
        taskSixStageThreeProjectSix.setName("Согласование и экспертиза рабочей и проектной документации");
        taskSixStageThreeProjectSix.setStartDate(Instant.parse("2022-06-10T00:00:00Z"));
        taskSixStageThreeProjectSix.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskSixStageThreeProjectSix.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        taskSixStageThreeProjectSix.setHead(foremanTaskSixStageThreeProjectSix);
        taskRepository.save(taskSixStageThreeProjectSix);


        final Person foremanStageThreeProjectSix = new Person();
        foremanStageThreeProjectSix.setFirstName("Алексей");
        foremanStageThreeProjectSix.setSurname("Морозов");
        foremanStageThreeProjectSix.setFatherName("Евгеньевич");
        foremanStageThreeProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanStageThreeProjectSix);

        final Stage stageThreeProjectSix = new Stage();
        stageThreeProjectSix.setName("Проектирование здания");
        stageThreeProjectSix.setStartDate(Instant.parse("2021-07-01T00:00:00Z"));
        stageThreeProjectSix.setActualEndDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageThreeProjectSix.setExpectedEndDate(Instant.parse("2022-07-01T00:00:00Z"));
        stageThreeProjectSix.setTasks(List.of(taskOneStageThreeProjectSix, taskTwoStageThreeProjectSix, taskThreeStageThreeProjectSix, taskSixStageThreeProjectSix));
        stageThreeProjectSix.setHead(foremanStageThreeProjectSix);
        stageRepository.save(stageThreeProjectSix);

        // endregion STAGE 3

        // region STAGE 4

        final Person foremanTaskOneStageFourProjectSix = new Person();
        foremanTaskOneStageFourProjectSix.setFirstName("Вадим");
        foremanTaskOneStageFourProjectSix.setSurname("Владим");
        foremanTaskOneStageFourProjectSix.setFatherName("Евгеньевич");
        foremanTaskOneStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskOneStageFourProjectSix);

        final Task taskOneStageFourProjectSix = new Task();
        taskOneStageFourProjectSix.setName("Рытьё котлована под фундамент");
        taskOneStageFourProjectSix.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        taskOneStageFourProjectSix.setActualEndDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskOneStageFourProjectSix.setExpectedEndDate(Instant.parse("2022-08-15T00:00:00Z"));
        taskOneStageFourProjectSix.setHead(foremanTaskOneStageFourProjectSix);
        taskOneStageFourProjectSix.setWorkers(List.of(foremanTaskOneStageFourProjectSix));
        taskRepository.save(taskOneStageFourProjectSix);

        final Person foremanTaskTwoStageFourProjectSix = new Person();
        foremanTaskTwoStageFourProjectSix.setFirstName("Евгений");
        foremanTaskTwoStageFourProjectSix.setSurname("Морозов");
        foremanTaskTwoStageFourProjectSix.setFatherName("Евгеньевич");
        foremanTaskTwoStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskTwoStageFourProjectSix);

        final Task taskTwoStageFourProjectSix = new Task();
        taskTwoStageFourProjectSix.setName("Строительство и гидроизоляция фундамента");
        taskTwoStageFourProjectSix.setStartDate(Instant.parse("2022-08-17T00:00:00Z"));
        taskTwoStageFourProjectSix.setActualEndDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskTwoStageFourProjectSix.setExpectedEndDate(Instant.parse("2022-09-28T00:00:00Z"));
        taskTwoStageFourProjectSix.setHead(foremanTaskTwoStageFourProjectSix);
        taskTwoStageFourProjectSix.setWorkers(List.of(foremanTaskTwoStageFourProjectSix));
        taskRepository.save(taskTwoStageFourProjectSix);

        final Person foremanTaskThreeStageFourProjectSix = new Person();
        foremanTaskThreeStageFourProjectSix.setFirstName("Петр");
        foremanTaskThreeStageFourProjectSix.setSurname("Сидоров");
        foremanTaskThreeStageFourProjectSix.setFatherName("Алексеевич");
        foremanTaskThreeStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskThreeStageFourProjectSix);

        final Task taskThreeStageFourProjectSix = new Task();
        taskThreeStageFourProjectSix.setName("Возведение стен и перекрытие этажей");
        taskThreeStageFourProjectSix.setStartDate(Instant.parse("2022-09-18T00:00:00Z"));
        taskThreeStageFourProjectSix.setActualEndDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskThreeStageFourProjectSix.setExpectedEndDate(Instant.parse("2023-12-31T00:00:00Z"));
        taskThreeStageFourProjectSix.setHead(foremanTaskThreeStageFourProjectSix);
        taskThreeStageFourProjectSix.setWorkers(List.of(foremanTaskThreeStageFourProjectSix));
        taskRepository.save(taskThreeStageFourProjectSix);

        final Person foremanTaskFourStageFourProjectSix = new Person();
        foremanTaskFourStageFourProjectSix.setFirstName("Захар");
        foremanTaskFourStageFourProjectSix.setSurname("Бурунов");
        foremanTaskFourStageFourProjectSix.setFatherName("Игорьевич");
        foremanTaskFourStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFourStageFourProjectSix);

        final Task taskFourStageFourProjectSix = new Task();
        taskFourStageFourProjectSix.setName("Строительство крыши и навесов");
        taskFourStageFourProjectSix.setStartDate(Instant.parse("2023-11-15T00:00:00Z"));
        taskFourStageFourProjectSix.setActualEndDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFourStageFourProjectSix.setExpectedEndDate(Instant.parse("2024-05-31T00:00:00Z"));
        taskFourStageFourProjectSix.setHead(foremanTaskFourStageFourProjectSix);
        taskFourStageFourProjectSix.setWorkers(List.of(foremanTaskFourStageFourProjectSix));
        taskRepository.save(taskFourStageFourProjectSix);

        final Person foremanTaskFiveStageFourProjectSix = new Person();
        foremanTaskFiveStageFourProjectSix.setFirstName("Влад");
        foremanTaskFiveStageFourProjectSix.setSurname("Шипилев");
        foremanTaskFiveStageFourProjectSix.setFatherName("Ильич");
        foremanTaskFiveStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskFiveStageFourProjectSix);

        final Task taskFiveStageFourProjectSix = new Task();
        taskFiveStageFourProjectSix.setName("Внешняя отделка стен дома");
        taskFiveStageFourProjectSix.setStartDate(Instant.parse("2024-04-27T00:00:00Z"));
        taskFiveStageFourProjectSix.setActualEndDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskFiveStageFourProjectSix.setExpectedEndDate(Instant.parse("2024-11-30T00:00:00Z"));
        taskFiveStageFourProjectSix.setHead(foremanTaskFiveStageFourProjectSix);
        taskFiveStageFourProjectSix.setWorkers(List.of(foremanTaskFiveStageFourProjectSix));
        taskRepository.save(taskFiveStageFourProjectSix);

        final Person foremanTaskSixStageFourProjectSix = new Person();
        foremanTaskSixStageFourProjectSix.setFirstName("Ибрагим");
        foremanTaskSixStageFourProjectSix.setSurname("Носов");
        foremanTaskSixStageFourProjectSix.setFatherName("Олегович");
        foremanTaskSixStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSixStageFourProjectSix);

        final Task taskSixStageFourProjectSix = new Task();
        taskSixStageFourProjectSix.setName("Проведение коммуникаций");
        taskSixStageFourProjectSix.setStartDate(Instant.parse("2024-10-15T00:00:00Z"));
        taskSixStageFourProjectSix.setActualEndDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSixStageFourProjectSix.setExpectedEndDate(Instant.parse("2025-06-30T00:00:00Z"));
        taskSixStageFourProjectSix.setHead(foremanTaskSixStageFourProjectSix);
        taskSixStageFourProjectSix.setWorkers(List.of(foremanTaskSixStageFourProjectSix));
        taskRepository.save(taskSixStageFourProjectSix);

        final Person foremanTaskSevenStageFourProjectSix = new Person();
        foremanTaskSevenStageFourProjectSix.setFirstName("Алексей");
        foremanTaskSevenStageFourProjectSix.setSurname("Морозов");
        foremanTaskSevenStageFourProjectSix.setFatherName("Евгеньевич");
        foremanTaskSevenStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskSevenStageFourProjectSix);

        final Task taskSevenStageFourProjectSix = new Task();
        taskSevenStageFourProjectSix.setName("Внутренняя отделка");
        taskSevenStageFourProjectSix.setStartDate(Instant.parse("2025-07-27T00:00:00Z"));
        taskSevenStageFourProjectSix.setActualEndDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskSevenStageFourProjectSix.setExpectedEndDate(Instant.parse("2026-02-01T00:00:00Z"));
        taskSevenStageFourProjectSix.setHead(foremanTaskSevenStageFourProjectSix);
        taskRepository.save(taskSevenStageFourProjectSix);

        final Person foremanTaskEightStageFourProjectSix = new Person();
        foremanTaskEightStageFourProjectSix.setFirstName("Иван");
        foremanTaskEightStageFourProjectSix.setSurname("Мегчов");
        foremanTaskEightStageFourProjectSix.setFatherName("Петрович");
        foremanTaskEightStageFourProjectSix.setRole(Role.FOREMAN);
        personRepository.save(foremanTaskEightStageFourProjectSix);

        final Task taskEightStageFourProjectSix = new Task();
        taskEightStageFourProjectSix.setName("Оформление дизайна и ландшафтный дизайн");
        taskEightStageFourProjectSix.setStartDate(Instant.parse("2026-02-08T00:00:00Z"));
        taskEightStageFourProjectSix.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        taskEightStageFourProjectSix.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        taskEightStageFourProjectSix.setHead(foremanTaskEightStageFourProjectSix);
        taskRepository.save(taskEightStageFourProjectSix);

        final Stage stageFourProjectSix = new Stage();
        stageFourProjectSix.setName("Возведение здания");
        stageFourProjectSix.setStartDate(Instant.parse("2022-08-07T00:00:00Z"));
        stageFourProjectSix.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        stageFourProjectSix.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        stageFourProjectSix.setTasks(List.of(taskOneStageFourProjectSix, taskTwoStageFourProjectSix, taskFiveStageFourProjectSix, taskEightStageFourProjectSix));
        stageFourProjectSix.setHead(foremanTaskEightStageFourProjectSix);
        stageRepository.save(stageFourProjectSix);

        // endregion STAGE 4

        final Project sixthProject = new Project();
        sixthProject.setName("Строительство многоквартирного дома в г.Новороссийск, ул. Прямая, д.7");
        sixthProject.setStartDate(Instant.parse("2019-05-01T00:00:00Z"));
        sixthProject.setActualEndDate(Instant.parse("2026-04-17T00:00:00Z"));
        sixthProject.setExpectedEndDate(Instant.parse("2026-05-01T00:00:00Z"));
        sixthProject.setStages(List.of(stageOneProjectSix, stageTwoProjectSix, stageThreeProjectSix, stageFourProjectSix));
        projectRepository.save(sixthProject);


        // endregion PROJECT 6

        return ResponseEntity.ok(projectRepository.findAll());
    }
}