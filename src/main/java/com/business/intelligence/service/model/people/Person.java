package com.business.intelligence.service.model.people;

import com.business.intelligence.service.model.constructionregion.Task;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name = "persons")
@Getter
@Setter
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String login;

    private String firstName;
    private String surname;
    private String fatherName;
    private String password;
    private Role role;

    @ManyToOne
    private Task task;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", fatherName='" + fatherName + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
