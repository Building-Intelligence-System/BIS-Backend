package com.business.intelligence.service.model.shifts;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Getter
@Setter
@Table(name = "personshifts")
@EqualsAndHashCode
@ToString
public class PersonShifts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private boolean isEnding;

    private Integer personId;
    private Instant scanTime;
}
