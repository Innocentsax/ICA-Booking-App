package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.Normal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NormalRepository extends JpaRepository<Normal, Long> {
    Optional<Normal> findBySeatNo(Integer seatNo);
}
