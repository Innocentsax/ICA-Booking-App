package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.Attendee;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    @Override
    List<Attendee> findAll(Sort sort);
}
