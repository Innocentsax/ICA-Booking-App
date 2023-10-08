package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.SaveAttendeeResponseDto;
import dev.icaApp.ICABookingApp.dto.TicketBookingDTO;
import dev.icaApp.ICABookingApp.model.Attendee;

public interface IAttendeeService {
    SaveAttendeeResponseDto saveAttendee (TicketBookingDTO ticketBookingDTO);
}
