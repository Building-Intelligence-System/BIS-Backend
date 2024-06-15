package com.business.intelligence.service.model.shifts;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class PersonShiftDto {

    private Integer personId;
    private LocalTime time;
}
