package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class TicketBaseEntity extends AuditBaseEntity{
    private Integer seatNo;
    private Integer status;
}
