package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.VIP1;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VIP1Repository extends JpaRepository<VIP1, Long> {
    Optional<VIP1> findBySeatNo(Integer seatNo);
}
