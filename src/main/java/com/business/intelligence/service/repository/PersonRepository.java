package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.people.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findById(Integer id);
    Optional<Person> findByLogin(String login);
}
