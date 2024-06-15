package com.business.intelligence.service.model.shifts;

import lombok.*;

import java.time.Instant;

@Data
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class QrEntity {

    private Integer projectId;
    private Instant time;
    private String password;

}
