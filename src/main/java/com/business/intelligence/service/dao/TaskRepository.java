package com.business.intelligence.service.dao;

import com.business.intelligence.service.model.constructionregion.Task;
import com.business.intelligence.service.model.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findById(Integer id);
}
