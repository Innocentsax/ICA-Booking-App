package dev.icaApp.ICABookingApp.controller;

import dev.icaApp.ICABookingApp.dto.SeatDetailsDto;
import dev.icaApp.ICABookingApp.service.IAttendeeService;
import dev.icaApp.ICABookingApp.service.ITicketServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AttendeeController {
    private final ITicketServices ticketServices;
    private final IAttendeeService attendeeService;
    @GetMapping("/")
    public ModelAndView home(){
        SeatDetailsDto seatDetailsDto = ticketServices.getAllSeatsInfo();
        return new  ModelAndView("index").addObject(seatDetailsDto);
    }

    @GetMapping("allBookings")
    public ModelAndView allBookings(){
        return new ModelAndView("admin_index").addObject(attendeeService.getAllAttendees());
    }

}
