package dev.icaApp.ICABookingApp.repostory;

import dev.icaApp.ICABookingApp.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transactions, Long> {
    Optional<Transactions> findByTicketId(Integer ticketId);
}
