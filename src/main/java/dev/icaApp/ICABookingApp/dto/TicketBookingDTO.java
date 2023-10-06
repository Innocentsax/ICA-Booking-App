package dev.icaApp.ICABookingApp.dto;

import dev.icaApp.ICABookingApp.model.Attendee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TicketBookingDTO {
    private List<AttendeeDto> attendees;
}


