package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.model.Attendee;
import dev.icaApp.ICABookingApp.repostory.AttendeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttendeeService implements IAttendeeService {
    private final AttendeeRepository attendeeRepository;

    @Override
    public Attendee saveAttendee(Attendee attendee) {
        return attendeeRepository.save(attendee);
    }

    public List<Attendee> getAllAttendee(){
        return attendeeRepository.findAll();
    }
}
