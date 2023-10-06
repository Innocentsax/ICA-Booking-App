package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Attendee extends BaseEntity{
    private String name;
    private String phoneNumber;
    private String email;
    private Long ICACardNumber;
}
