package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public class TicketInfo extends BaseEntity {
    private String ticketCategory;
    private double cost;
    private double discountCost;
}
