package com.business.intelligence.service.model.building;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "projects")
@EqualsAndHashCode
@ToString
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant startDate;
    private Instant actualEndDate;
    private ConstructionType constructionType;
    private String imageReference;

    private String address;
    private Double latitude;
    private Double longitude;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Stage> stages = new ArrayList<>();
}
