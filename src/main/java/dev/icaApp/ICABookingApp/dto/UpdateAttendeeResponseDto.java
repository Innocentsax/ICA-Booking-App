package dev.icaApp.ICABookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class UpdateAttendeeResponseDto {
    private String status;
    private String message;
}
