package com.business.intelligence.service.dao;

import com.business.intelligence.service.model.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(Integer id);
}
