package com.business.intelligence.service.model.constructionregion;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant start;
    private Instant end;
    private Integer expectedDuration;
    private String address;
    private Double latitude;
    private Double longitude;
    private ConstructionType type;
    private String imageReference;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Stage> stages;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", expectedDuration=" + expectedDuration +
                ", address=" + address +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", type=" + type +
                ", imageReference=" + imageReference +
                '}';
    }
}
