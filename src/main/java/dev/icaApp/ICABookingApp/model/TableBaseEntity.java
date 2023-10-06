package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class TableBaseEntity extends BaseEntity {
    private Integer availableSeats;
    private double cost;
}
