package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class PaidAttendee extends BaseEntity{
    private String name;
    private String phoneNumber;
    private String email;
    private Long ICACardNumber;
    private Long ticketNumber;
}
