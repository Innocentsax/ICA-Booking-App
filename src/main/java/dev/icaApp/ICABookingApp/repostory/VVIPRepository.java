package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.VVIP;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VVIPRepository extends JpaRepository<VVIP, Long> {
    Optional<VVIP> findBySeatNo(Integer seatNo);
}
