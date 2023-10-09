package dev.icaApp.ICABookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateAttendeeDto {
    private Long id;
    private String passKey;
    private String adminId;
    private Integer seatNo;
    private String ticketCategory;
    private String ticketId;
}
