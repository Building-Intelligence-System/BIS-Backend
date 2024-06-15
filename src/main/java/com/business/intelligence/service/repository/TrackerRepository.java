package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.trackers.Tracker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackerRepository extends JpaRepository<Tracker, Integer> {

    Optional<Tracker> findByImei(String imei);
}
