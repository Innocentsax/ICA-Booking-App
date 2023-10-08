package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.TicketIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketIdsRepository extends JpaRepository<TicketIds, Long> {
}
