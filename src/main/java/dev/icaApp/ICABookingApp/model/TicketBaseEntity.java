package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;

@MappedSuperclass
@Data
public abstract class TicketBaseEntity extends AuditBaseEntity{
    private Integer seatNo;
    private Integer status;
}
