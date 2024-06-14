package com.business.intelligence.service.model.constructionregion;

import com.business.intelligence.service.model.people.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Instant begin;
    private Instant end;
    private Integer expectedDuration;

    @OneToOne
    private Person head;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private List<Person> workers;

    @ManyToOne
    private Stage stage;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", begin=" + begin +
                ", end=" + end +
                ", expectedDuration=" + expectedDuration +
                '}';
    }
}
