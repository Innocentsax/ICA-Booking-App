package dev.icaApp.ICABookingApp.controller;

import dev.icaApp.ICABookingApp.dto.SeatDetailsDto;
import dev.icaApp.ICABookingApp.service.ITicketServices;
import dev.icaApp.ICABookingApp.service.TicketServices;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AttendeeController {
   private final ITicketServices ticketServices;
    @GetMapping("/")
    public ModelAndView home(){
        SeatDetailsDto seatDetailsDto = ticketServices.getAllSeatsInfo();
        return new  ModelAndView("index").addObject(seatDetailsDto);
    }

}
