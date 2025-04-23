package com.project.projectxxx.domain.room;

import com.project.projectxxx.domain.PeriodTime;
import com.project.projectxxx.domain.Room;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("D")
public class Dayuse extends Room {
    @Embedded
    private PeriodTime time;
}
