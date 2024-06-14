package com.business.intelligence.service;

import com.business.intelligence.service.dao.PersonRepository;
import com.business.intelligence.service.model.constructionregion.Task;
import com.business.intelligence.service.model.people.Person;
import com.business.intelligence.service.model.people.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.List;

public class ConnectionTest {

//    @Autowired
    PersonRepository repository;


    @Test
    public void connectionTest() {
        final Person person = new Person();

        person.setId(1);
        person.setFirstName("alex");
        person.setSurname("morozov");
        person.setFatherName("evgenievich");

        person.setRole(Role.WORKER);

        final Task task = new Task();

        task.setId(1);
        task.setName("Бетонирование");
        task.setBegin(Instant.now());

        task.setEnd(Instant.MAX);

        task.setExpectedDuration(100);
        task.setWorkers(List.of(person));
        person.setTask(task);

        repository.save(person);

        final Person dbPerson = repository.findById(1).get();

        int res = 0;
    }
}
