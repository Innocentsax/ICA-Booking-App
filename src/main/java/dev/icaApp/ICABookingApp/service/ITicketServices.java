package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.SeatDetailsDto;
import dev.icaApp.ICABookingApp.model.Normal;
import dev.icaApp.ICABookingApp.model.VIP1;
import dev.icaApp.ICABookingApp.model.VIP2;
import dev.icaApp.ICABookingApp.model.VVIP;

public interface ITicketServices {
     SeatDetailsDto getAllSeatsInfo();
     VIP1 saveVIP1(VIP1 vip1);
     VIP2 saveVIP2(VIP2 vip2);
     VVIP saveVVIP(VVIP vvip);
     Normal saveNormal(Normal normal);
}
