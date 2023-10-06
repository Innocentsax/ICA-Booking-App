package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
}
