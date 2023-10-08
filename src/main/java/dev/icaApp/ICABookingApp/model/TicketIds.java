package dev.icaApp.ICABookingApp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TicketIds {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_ids_sequence")
    @SequenceGenerator(name = "ticket_ids_sequence", sequenceName = "ticket_ids_sequence", allocationSize = 1, initialValue = 100)
    private Integer id;
    private String email;
}
