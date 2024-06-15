package com.business.intelligence.service.model.building;

import com.business.intelligence.service.model.person.Person;
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
@Table(name = "tasks")
@EqualsAndHashCode
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Instant startDate;
    private Instant expectedEndDate;
    private Instant actualEndDate;
    private TaskState taskState;

    @OneToOne
    private Person head;
    @OneToMany
    private List<Person> workers = new ArrayList<>();

    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    private Stage stage;
}
