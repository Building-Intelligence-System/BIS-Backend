package com.business.intelligence.service.model.foremananalytics;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ForemanAnalyticsDto {

    private Integer foremanId;
    private Long averageDiffTime;
}
