package dev.icaApp.ICABookingApp.dto;

import dev.icaApp.ICABookingApp.model.Attendee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AttendeeDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Long ICACardNumber;
    private Integer ticketId;
    private boolean origin=false;
    private String ticketCategory;
    private String seatNo;
    private String status;

    public AttendeeDto (Attendee attendee){
        this.name=attendee.getName();
        this.phoneNumber=attendee.getPhoneNumber();
        this.email= attendee.getEmail();
        this.ICACardNumber=attendee.getICACardNumber();
        this.ticketId=attendee.getTicketId();
        this.origin=attendee.isOrigin();
        this.ticketCategory=attendee.getTicketCategory();
        this.seatNo=attendee.getSeatNo();
        this.status=attendee.getStatus();
    }
}


