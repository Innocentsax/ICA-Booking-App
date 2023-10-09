package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.*;
import dev.icaApp.ICABookingApp.model.*;
import dev.icaApp.ICABookingApp.repostory.*;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttendeeService implements IAttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final TicketIdsRepository ticketIdsRepository;
    private final EmailService emailService;
    private final VVIPRepository vvipRepository;
    private final VIP1Repository vip1Repository;
    private final VIP2Repository vip2Repository;
    private final NormalRepository normalRepository;
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
                "  <head>\n" + "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
                "    <meta charset=\"UTF-8\" />" +
                        "<body>"+
                            "<h3 class='text-success'>ICA EVENT -> Seat Booking</h3>"+
                            "<p class='text-success'> Hello "+saveAttendeeResponseDto.getName()+ ", please proceed to make payment for the selected seats."+
                            "<div class='card'>"+
                                "<p>Ticket No: ICA-TK-"+saveAttendeeResponseDto.getTicketId()+"</p>"+
                                "<p>Total Cost: <span>&#8358;</span>" +saveAttendeeResponseDto.getTotalCost()+"</p>"+
                                "<p>Bank Name: <b>Access Bank</b></p>"+
                                "<p>Account Name: <b>India Cultural Association</b></p>"+
                                "<p>Account Number: <b>0003328229</b></p>"+
                            "</div>"+
                            "<p class='text-info'>Please make reference to the <b>TicketID</b> when making payment, feel free to use it as the depositor name</p>"+
                            "<p class='text-danger'>Note: Do note that the seats remain available till payment is made. Also, if payment is not received within 6 hours your selection will be cleared </p>"+
                        "</body>"+
                "<html>";
        emailService.sendHTMLEmail(saveAttendeeResponseDto.getEmail(),"ICA Seat Booking",emailContent);
        return saveAttendeeResponseDto;

    }

    @Override
    public List<Attendee> getAllAttendees() {
        return attendeeRepository.findAll();
    }

    @Override
    public UpdateAttendeeResponseDto updateAttendeeStatus(UpdateAttendeeDto updateAttendeeDto) {
        String adminKey ="ICA-ADMIN-PASS";
        String adminId="ALPHA";
        if(updateAttendeeDto.getPassKey().equals(adminKey) && updateAttendeeDto.getAdminId().equals(adminId)){
            Optional<Attendee> attendee = attendeeRepository.findById(updateAttendeeDto.getId());
            if(attendee.isPresent()) {
                Attendee attendee1 = attendee.get();
                attendee1.setStatus("Paid");
                switch (attendee1.getTicketCategory()) {
                    case "VVIP" -> {
                        Optional<VVIP> vvip = vvipRepository.findBySeatNo(attendee1.getSeatNo());
                        VVIP vvip1 = vvip.get();
                        vvip1.setStatus(2);
                        vvipRepository.save(vvip1);
                    }
                    case "VIP1" -> {
                        Optional<VIP1> vip = vip1Repository.findBySeatNo(attendee1.getSeatNo());
                        VIP1 vip1 = vip.get();
                        vip1.setStatus(2);
                        vip1Repository.save(vip1);
                    }
                    case "VIP2" -> {
                        Optional<VIP2> vip2 = vip2Repository.findBySeatNo(attendee1.getSeatNo());
                        VIP2 vip2N = vip2.get();
                        vip2N.setStatus(2);
                        vip2Repository.save(vip2N);
                    }
                    case "NORMAL" -> {
                        Optional<Normal> normal = normalRepository.findBySeatNo(attendee1.getSeatNo());
                        Normal normal1 = normal.get();
                        normal1.setStatus(2);
                        normalRepository.save(normal1);
                    }
                }
                return  UpdateAttendeeResponseDto.builder().status("Success").message("Payment was successfully updated").build();

            }
            return UpdateAttendeeResponseDto.builder().message("Error, record not found").status("failed").build();
        }else{
            return UpdateAttendeeResponseDto.builder().message("Error, record not found").status("invalid").build();
        }
        //RETRIEVE ATTENDEE

    }

    public List<Attendee> getAllAttendee(){
        //return attendeeRepository.findAllByIdOrderByIdAsc();
        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        return attendeeRepository.findAll(sort);
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

    public boolean isAfter6Hours(LocalDateTime startDate) {
        LocalDateTime now = LocalDateTime.now();
        long hours =  ChronoUnit.HOURS.between(startDate, now);

        return hours >= 6;
    }

    @Scheduled(cron = "0 * * * * ?")
    public void checkSelectedTicketStatus() {
        List<VVIP> vvipList = vvipRepository.findAll();
        List<VIP1> vip1List = vip1Repository.findAll();
        List<VIP2> vip2List = vip2Repository.findAll();
        List<Normal> normalList = normalRepository.findAll();

        for(VVIP vvip : vvipList) {
            if (vvip.getStatus() == 1)
                if(isAfter6Hours(vvip.getCreatedAt())) vvip.setStatus(0);
        }

        for(VIP1 vip1 : vip1List) {
            if (vip1.getStatus() == 1)
                if(isAfter6Hours(vip1.getCreatedAt())) vip1.setStatus(0);
        }

        for(VIP2 vip2 : vip2List) {
            if (vip2.getStatus() == 1)
                if(isAfter6Hours(vip2.getCreatedAt())) vip2.setStatus(0);
        }

        for(Normal normal : normalList) {
            if (normal.getStatus() == 1)
                if(isAfter6Hours(normal.getCreatedAt())) normal.setStatus(0);
        }
    }


}
