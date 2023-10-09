package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.VIP2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VIP2Repository extends JpaRepository<VIP2, Long> {
    Optional<VIP2> findBySeatNo(Integer seatNo);
}
