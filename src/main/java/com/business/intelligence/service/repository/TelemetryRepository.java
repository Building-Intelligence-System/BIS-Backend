package com.business.intelligence.service.repository;

import com.business.intelligence.service.model.telemetry.Telemetry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TelemetryRepository extends JpaRepository<Telemetry, Integer> {

    List<Telemetry> findAllByImeiOrderByTimeDesc(String imei);
}
