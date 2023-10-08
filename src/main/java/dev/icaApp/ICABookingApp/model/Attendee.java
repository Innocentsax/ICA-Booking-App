package dev.icaApp.ICABookingApp.model;

import dev.icaApp.ICABookingApp.dto.AttendeeDto;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Attendee extends AuditBaseEntity{
    private String name;
    private String phoneNumber;
    private String email;
    private Integer ICACardNumber;
    private Integer ticketId;
    private boolean origin;
    private String ticketCategory;
    private Integer seatNo;
    private String status;

    public Attendee(AttendeeDto attendeeDto){
        this.name=attendeeDto.getName();
        this.phoneNumber=attendeeDto.getPhoneNumber();
        this.email= attendeeDto.getEmail();
        this.ICACardNumber=attendeeDto.getICACardNumber();
        this.ticketId=attendeeDto.getTicketId();
        this.origin=attendeeDto.isOrigin();
        this.ticketCategory=attendeeDto.getTicketCategory();
        this.seatNo=attendeeDto.getSeatNo();
        this.status=attendeeDto.getStatus();
        this.setId(attendeeDto.getId());
    }
}
