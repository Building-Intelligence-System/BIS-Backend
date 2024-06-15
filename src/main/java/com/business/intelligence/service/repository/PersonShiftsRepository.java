package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.shifts.PersonShifts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonShiftsRepository extends JpaRepository<PersonShifts, Integer> {

    Optional<PersonShifts> findById(Integer id);

    List<PersonShifts> findAllByPersonId(Integer id);

    @Query("SELECT p FROM PersonShifts  p where p.personId in :ids")
    List<PersonShifts> findAllByPersonIds(List<Integer> ids);


}
