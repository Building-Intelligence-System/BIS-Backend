package com.business.intelligence.service.model.telemetry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "telemetries")
public class Telemetry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imei;
    private Instant time;

    private Boolean valid;

    private Double latitude;
    private Double longitude;
    private Short altitude;
    private Short speed;
    private Short course;
    private Short satellitesCount;

}
