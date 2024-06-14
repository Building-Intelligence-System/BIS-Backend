package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.PersonShifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonDayShiftRepository extends JpaRepository<PersonShifts, Integer> {

    Optional<PersonShifts> findById(Integer id);
}
