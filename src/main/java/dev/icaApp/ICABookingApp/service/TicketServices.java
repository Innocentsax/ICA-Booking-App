package dev.icaApp.ICABookingApp.service;

import dev.icaApp.ICABookingApp.dto.SeatDetailsDto;
import dev.icaApp.ICABookingApp.model.Normal;
import dev.icaApp.ICABookingApp.model.VIP1;
import dev.icaApp.ICABookingApp.model.VIP2;
import dev.icaApp.ICABookingApp.model.VVIP;
import dev.icaApp.ICABookingApp.repostory.NormalRepository;
import dev.icaApp.ICABookingApp.repostory.VIP1Repository;
import dev.icaApp.ICABookingApp.repostory.VIP2Repository;
import dev.icaApp.ICABookingApp.repostory.VVIPRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@RequiredArgsConstructor
public class TicketServices implements ITicketServices{
    private final VIP1Repository vip1Repository;
    private final VIP2Repository vip2Repository;
    private final VVIPRepository vvipRepository;
    private final NormalRepository normalRepository;
    @Override
    public SeatDetailsDto getAllSeatsInfo() {
        List<VIP1> vip1 = vip1Repository.findAll();
        List<VIP2> vip2 = vip2Repository.findAll();
        List<VVIP> vvip= vvipRepository.findAll();
        List<Normal> normal = normalRepository.findAll();
        return SeatDetailsDto.builder().vip1(vip1)
                .vip2(vip2)
                .vvip(vvip)
                .normal(normal)
                .build();
    }
    @Override
    public VIP1 saveVIP1(VIP1 vip1){
        return vip1Repository.save(vip1);
    }
    @Override
    public VIP2 saveVIP2(VIP2 vip2) {
        return vip2Repository.save(vip2);
    }
    @Override
    public VVIP saveVVIP(VVIP vvip) {
        return vvipRepository.save(vvip);
    }
    @Override
    public Normal saveNormal(Normal normal) {
        return normalRepository.save(normal);
    }

}
