package com.business.intelligence.service.dto;

import lombok.*;

@Data
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ProjectBiInfoDto {

    private Integer projectId;
    private Double expectedDonePercent;
    private Double actualDonePercent;
}
