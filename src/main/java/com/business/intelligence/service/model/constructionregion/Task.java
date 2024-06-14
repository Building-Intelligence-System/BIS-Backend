package com.business.intelligence.service.model.constructionregion;

import com.business.intelligence.service.model.people.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Instant start;
    private Instant end;
    private Integer expectedDuration;

    @OneToOne
    private Person head;
    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER)
    private List<Person> workers;

    @ManyToOne
    private Stage stage;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Task task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(name, task.name)
                && Objects.equals(start, task.start)
                && Objects.equals(end, task.end)
                && Objects.equals(expectedDuration, task.expectedDuration)
                && Objects.equals(head, task.head)
                && Objects.equals(workers, task.workers)
                && Objects.equals(stage, task.stage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, expectedDuration, head, workers, stage);
    }


    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", expectedDuration=" + expectedDuration +
                '}';
    }


//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .append("id", id)
//                .append("name", name)
//                .append("start", start)
//                .append("end", end)
//                .append("expectedDuration", expectedDuration)
//                .toString();
//    }
}
