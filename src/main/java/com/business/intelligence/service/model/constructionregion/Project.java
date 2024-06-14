package com.business.intelligence.service.model.constructionregion;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant start;
    private Instant end;
    private Integer expectedDuration;

    @OneToMany
    private List<Stage> stages;
}
