package dev.icaApp.ICABookingApp.dto;

import dev.icaApp.ICABookingApp.model.Normal;
import dev.icaApp.ICABookingApp.model.VIP1;
import dev.icaApp.ICABookingApp.model.VIP2;
import dev.icaApp.ICABookingApp.model.VVIP;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SeatDetailsDto {
    List<VIP1> vip1;
    List<VIP2> vip2;
    List<VVIP> vvip;
    List<Normal> normal;
}
