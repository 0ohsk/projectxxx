package com.project.projectxxx.domain.room;

import com.project.projectxxx.domain.PeriodDate;
import com.project.projectxxx.domain.Room;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue("L")
public class Lodging extends Room {
    @Embedded
    private PeriodDate date;
}
