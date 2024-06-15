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
@Table(name = "stages")
@EqualsAndHashCode
@ToString
public class Stage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant startDate;
    private Instant expectedEndDate;
    private Instant actualEndDate;

    @OneToOne(fetch = FetchType.EAGER)
    private Person head;
    @ManyToOne(fetch = FetchType.EAGER)
    private Project project;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();
}