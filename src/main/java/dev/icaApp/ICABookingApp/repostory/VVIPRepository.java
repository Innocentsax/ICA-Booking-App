package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.VVIP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VVIPRepository extends JpaRepository<VVIP, Long> {
}
