package com.business.intelligence.service.model.building;

import com.business.intelligence.service.model.person.Person;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

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

    @OneToOne(fetch = FetchType.EAGER)
    private Person head;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Person> workers = new ArrayList<>();

    @OneToMany(fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Stage stage;
}
