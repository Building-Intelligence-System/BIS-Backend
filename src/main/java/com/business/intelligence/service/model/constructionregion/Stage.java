package com.business.intelligence.service.model.constructionregion;

import com.business.intelligence.service.model.people.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant begin;
    private Instant end;
    private Integer expectedDuration;

    @OneToOne
    private Person head;
    @OneToMany(mappedBy = "stage", fetch = FetchType.EAGER)
    private List<Task> tasks;
    @ManyToOne
    private Project project;

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", expectedDuration=" + expectedDuration +
                '}';
    }
}
