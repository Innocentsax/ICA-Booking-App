package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transactions extends AuditBaseEntity{
    private Integer ticketId;
    private double totalCost;
    private String status="Pending";
}
