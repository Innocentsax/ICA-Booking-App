package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.AttendeeDto;
import dev.icaApp.ICABookingApp.dto.SaveAttendeeResponseDto;
import dev.icaApp.ICABookingApp.dto.TicketBookingDTO;
import dev.icaApp.ICABookingApp.model.Attendee;
import dev.icaApp.ICABookingApp.model.TicketIds;
import dev.icaApp.ICABookingApp.repostory.AttendeeRepository;
import dev.icaApp.ICABookingApp.repostory.TicketIdsRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService implements IAttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final TicketIdsRepository ticketIdsRepository;
    private final EmailService emailService;
    @Override
    public SaveAttendeeResponseDto saveAttendee(TicketBookingDTO ticketBookingDTO) throws MessagingException {
        System.out.println(ticketBookingDTO);
        SaveAttendeeResponseDto saveAttendeeResponseDto=new SaveAttendeeResponseDto();
        boolean captured=false;
        AttendeeDto attendeeT=ticketBookingDTO.getAttendees().get(0);
        TicketIds ticketIds=ticketIdsRepository.save(TicketIds.builder().email(attendeeT.getEmail()).build());
        double ticketTotalCost=0;
        for (AttendeeDto attendeeDto: ticketBookingDTO.getAttendees()) {
            if(!captured){
                Attendee attendee = new Attendee(attendeeDto);
                attendee.setTicketId(ticketIds.getId());
                attendee.setStatus("Pending");
                Attendee attendeeRes=attendeeRepository.save(attendee);
                System.out.println(attendeeRes);
                saveAttendeeResponseDto.setEmail(attendeeRes.getEmail());
                saveAttendeeResponseDto.setName(attendee.getName());
                saveAttendeeResponseDto.setICACardNumber(attendeeRes.getICACardNumber());
                saveAttendeeResponseDto.addTicket(attendeeRes.getTicketCategory());
                saveAttendeeResponseDto.addSeats(attendeeRes.getSeatNo());
                saveAttendeeResponseDto.setPhoneNumber(attendeeRes.getPhoneNumber());
                saveAttendeeResponseDto.setICACardNumber(attendeeRes.getICACardNumber());
                ticketTotalCost=ticketTotalCost+computeSeatCost(true,attendeeRes.getICACardNumber(),attendeeRes.getTicketCategory());
                captured=true;

            }else {
                Attendee attendee = new Attendee(attendeeDto);
                attendee.setTicketId(ticketIds.getId());
                attendee.setStatus("Pending");
                Attendee attendeeRes = attendeeRepository.save(attendee);
                saveAttendeeResponseDto.addTicket(attendeeRes.getTicketCategory());
                saveAttendeeResponseDto.addSeats(attendeeRes.getSeatNo());
                ticketTotalCost = ticketTotalCost + computeSeatCost(false, attendeeRes.getICACardNumber(), attendeeRes.getTicketCategory());
            }
        }
        saveAttendeeResponseDto.setTotalCost(ticketTotalCost);
        saveAttendeeResponseDto.setStatus("Pending");
        saveAttendeeResponseDto.setTicketId(ticketIds.getId());
        //SEND MAIL WITH TICKET INFO
        String emailContent=
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "  <head>\n" +
                "    <meta charset=\"UTF-8\" />" +
                        "<body>"+
                            "<h3>ICA EVENT -> Seat Booking</h3>"+
                            "<p class='text-success'> Hello "+saveAttendeeResponseDto.getName()+ ", please proceed to make payment for the selected seats."+
                            "<p>Ticket No: ICA-TK-"+saveAttendeeResponseDto.getTicketId()+"</p>"+
                            "<p>Total Cost: " +saveAttendeeResponseDto.getTotalCost()+"</p>"+
                            "<p>Bank Name: Sample Bank</p>"+
                            "<p>Total Cost: <span>&#8358;</span> 19020 </p>"+
                            "<p class='text-danger'>Important: Do note that the seats remain available till payment is made. Also, if payment is not received within 6 hours your selection will be cleared </p>"+
                        "</body>"+
                "<html>" +
                "";
        emailService.sendHTMLEmail(saveAttendeeResponseDto.getEmail(),"ICA Seat Booking",emailContent);
        return saveAttendeeResponseDto;

    }

    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    public List<Attendee> getAllAttendee(){
        return attendeeRepository.findAll();
    }

    private double computeSeatCost(boolean origin, Integer ICANumber, String ticketCategory){
        double ticketCost=0;
        if(origin && ICANumber !=null && ICANumber>1){
            switch (ticketCategory){
                case "VVIP"-> ticketCost =150000;
                case "VIP1"-> ticketCost = 100000;
                case "VIP2"-> ticketCost=60000;
                case "NORMAL"-> ticketCost=35000;
            }
        }else {
            switch (ticketCategory){
                case "VVIP"-> ticketCost =160000;
                case "VIP1"-> ticketCost = 110000;
                case "VIP2"-> ticketCost=70000;
                case "NORMAL"-> ticketCost=40000;
            }
        }
        return ticketCost;
    }


}
