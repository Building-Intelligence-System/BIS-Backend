package com.business.intelligence.service.model.people;

import com.business.intelligence.service.model.constructionregion.Task;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String firstName;
    private String surname;
    private String fatherName;
    private Role role;

    @ManyToOne
    private Task task;
}