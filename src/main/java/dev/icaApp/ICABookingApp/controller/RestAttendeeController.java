package dev.icaApp.ICABookingApp.controller;

import dev.icaApp.ICABookingApp.dto.*;
import dev.icaApp.ICABookingApp.model.Attendee;
import dev.icaApp.ICABookingApp.service.IAttendeeService;
import dev.icaApp.ICABookingApp.service.ITicketServices;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequiredArgsConstructor
@RequestMapping("api")
public class RestAttendeeController {
    private final ITicketServices ticketServices;
    private final IAttendeeService attendeeService;

    @GetMapping("allSeatsInfo")
    public ResponseEntity<SeatDetailsDto> getAllSeatsInfo() {
        System.out.println("I am here");
        return new ResponseEntity<>(ticketServices.getAllSeatsInfo(), HttpStatus.OK);
    }

    @PostMapping("saveBooking")
    public ResponseEntity<SaveAttendeeResponseDto> saveAttendee(@RequestBody TicketBookingDTO ticketBookingDTO) throws MessagingException {
        return new ResponseEntity(attendeeService.saveAttendee(ticketBookingDTO), HttpStatus.OK);
    }
    @GetMapping("allBookings")
    public ResponseEntity<List<Attendee>> allBookings(){
        return new ResponseEntity(attendeeService.getAllAttendees(), HttpStatus.OK);
    }

    @PostMapping("update_attendee_status")
    public ResponseEntity<UpdateAttendeeResponseDto> updatePayment(@RequestBody UpdateAttendeeDto updateAttendeeDto) throws MessagingException {
        return new ResponseEntity<>(attendeeService.updateAttendeeStatus(updateAttendeeDto),HttpStatus.OK);
    }



    //public ResponseEntity<>
}