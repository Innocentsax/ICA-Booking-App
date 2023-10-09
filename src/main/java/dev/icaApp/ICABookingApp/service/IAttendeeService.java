package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.SaveAttendeeResponseDto;
import dev.icaApp.ICABookingApp.dto.TicketBookingDTO;
import dev.icaApp.ICABookingApp.dto.UpdateAttendeeDto;
import dev.icaApp.ICABookingApp.dto.UpdateAttendeeResponseDto;
import dev.icaApp.ICABookingApp.model.Attendee;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IAttendeeService {
    SaveAttendeeResponseDto saveAttendee (TicketBookingDTO ticketBookingDTO) throws MessagingException;
    List<Attendee> getAllAttendees();
    UpdateAttendeeResponseDto updateAttendeeStatus(UpdateAttendeeDto updateAttendeeDto);
}
