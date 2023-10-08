package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.AttendeeDto;
import dev.icaApp.ICABookingApp.dto.SaveAttendeeResponseDto;
import dev.icaApp.ICABookingApp.dto.TicketBookingDTO;
import dev.icaApp.ICABookingApp.model.Attendee;
import dev.icaApp.ICABookingApp.model.TicketIds;
import dev.icaApp.ICABookingApp.repostory.AttendeeRepository;
import dev.icaApp.ICABookingApp.repostory.TicketIdsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService implements IAttendeeService {
    private final AttendeeRepository attendeeRepository;
    private final TicketIdsRepository ticketIdsRepository;

    @Override
    public SaveAttendeeResponseDto saveAttendee(TicketBookingDTO ticketBookingDTO) {
        SaveAttendeeResponseDto saveAttendeeResponseDto=new SaveAttendeeResponseDto();
        boolean captured=false;
        AttendeeDto attendeeT=ticketBookingDTO.getAttendees().get(0);
        TicketIds ticketIds=ticketIdsRepository.save(TicketIds.builder().email(attendeeT.getEmail()).build());
        for (AttendeeDto attendeeDto: ticketBookingDTO.getAttendees()) {
            if(!captured){
                Attendee attendee = new Attendee(attendeeDto);
                attendee.setTicketId(ticketIds.getId());
                Attendee attendeeRes=attendeeRepository.save(attendee);
                saveAttendeeResponseDto.setEmail(attendeeRes.getEmail());
                saveAttendeeResponseDto.setName(attendee.getName());
                saveAttendeeResponseDto.setICACardNumber(attendeeRes.getICACardNumber());
                saveAttendeeResponseDto.addTicket(attendeeRes.getTicketCategory());
                saveAttendeeResponseDto.addSeats(attendeeRes.getSeatNo());
                saveAttendeeResponseDto.setPhoneNumber(attendeeRes.getPhoneNumber());

            }
            Attendee attendee = new Attendee(attendeeDto);
            attendee.setId(attendeeT.getId());
            Attendee attendeeRes=attendeeRepository.save(attendee);
        }
        //return attendeeRepository.save(attendee);
        return saveAttendeeResponseDto;

    }

    public List<Attendee> getAllAttendee(){
        return attendeeRepository.findAll();
    }


}
