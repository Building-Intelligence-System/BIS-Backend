package com.business.intelligence.service.model.constructionregion;

import com.business.intelligence.service.model.people.Person;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant start;
    private Instant end;
    private Integer expectedDuration;

    @OneToOne
    private Person head;
    @OneToMany(mappedBy = "stage", fetch = FetchType.EAGER)
    private List<Task> tasks;
    @ManyToOne
    private Project project;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Stage stage = (Stage) o;
        return Objects.equals(id, stage.id) &&
                Objects.equals(name, stage.name)
                && Objects.equals(start, stage.start)
                && Objects.equals(end, stage.end)
                && Objects.equals(expectedDuration, stage.expectedDuration)
                && Objects.equals(head, stage.head)
                && Objects.equals(tasks, stage.tasks)
                && Objects.equals(project, stage.project);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, expectedDuration, head, tasks, project);
    }

    @Override
    public String toString() {
        return "Stage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", expectedDuration=" + expectedDuration +
                '}';
    }
}
