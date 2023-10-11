package dev.icaApp.ICABookingApp.controller;

import dev.icaApp.ICABookingApp.dto.SeatDetailsDto;
import dev.icaApp.ICABookingApp.model.Attendee;
import dev.icaApp.ICABookingApp.model.Transactions;
import dev.icaApp.ICABookingApp.service.IAttendeeService;
import dev.icaApp.ICABookingApp.service.ITicketServices;
import jakarta.transaction.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @GetMapping("admin/allBookings/123xyz/rx")
    public ModelAndView allBookings(){
        List<Attendee> attendees = attendeeService.getAllAttendees();
        System.out.println(attendees);
        return new ModelAndView("admin_index").addObject("allAttendees",attendees);
    }

    @GetMapping("admin/allTransactions/123xyz/rx")
    public ModelAndView allTransactions(){
        List<Transactions> transactions = attendeeService.getAllTransactions();
        System.out.println(transactions);
        return new ModelAndView("transactions").addObject("transactions",transactions);
    }

}
