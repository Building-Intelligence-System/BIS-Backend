package com.business.intelligence.service.model;

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

    private Integer personId;
    private Instant scanTime;
}
