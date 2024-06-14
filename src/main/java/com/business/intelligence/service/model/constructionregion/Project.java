package com.business.intelligence.service.model.constructionregion;

import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private Instant start;
    private Instant end;
    private Integer expectedDuration;

    @OneToMany(mappedBy = "project", fetch = FetchType.EAGER)
    private List<Stage> stages;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Project project = (Project) o;
        return Objects.equals(id, project.id) &&
                Objects.equals(name, project.name)
                && Objects.equals(start, project.start)
                && Objects.equals(end, project.end)
                && Objects.equals(expectedDuration, project.expectedDuration)
                && Objects.equals(stages, project.stages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, start, end, expectedDuration, stages);
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .append("start", start)
                .append("end1", end)
                .append("expectedDuration", expectedDuration)
                .append("stages", stages)
                .toString();
    }
}
