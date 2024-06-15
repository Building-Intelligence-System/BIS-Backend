package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.person.Person;
import com.business.intelligence.service.model.person.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByLogin(String login);
}
