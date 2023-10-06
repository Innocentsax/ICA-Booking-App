package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.PaidAttendee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaidAttendeeRepository extends JpaRepository<PaidAttendee, Long> {
}
