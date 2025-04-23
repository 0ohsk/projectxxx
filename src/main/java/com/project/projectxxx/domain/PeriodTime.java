package com.project.projectxxx.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class PeriodTime {
    LocalDateTime startTime;

    LocalDateTime endTime;
}
