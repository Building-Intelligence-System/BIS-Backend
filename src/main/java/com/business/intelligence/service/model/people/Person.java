package com.business.intelligence.service.model.people;

import com.business.intelligence.service.model.constructionregion.Task;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Data
@Table(name = "persons")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Person person = (Person) o;
        return Objects.equals(id, person.id) &&
                Objects.equals(firstName, person.firstName)
                && Objects.equals(surname, person.surname)
                && Objects.equals(fatherName, person.fatherName)
                && Objects.equals(role, person.role)
                && Objects.equals(task, person.task);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, surname, fatherName, role, task);
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname=" + surname +
                ", fatherName=" + fatherName +
                ", role=" + role +
                '}';
    }

//
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .append("id", id)
//                .append("firstName", firstName)
//                .append("surname", surname)
//                .append("fatherName", fatherName)
//                .append("role", role)
//                .toString();
//    }
}
