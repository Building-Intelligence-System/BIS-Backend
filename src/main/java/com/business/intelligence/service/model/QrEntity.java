package com.business.intelligence.service.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Data
@Getter
@Setter
public class QrEntity {

    private Integer buildingId;
    private Instant time;
    private String password;

}
