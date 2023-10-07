package dev.icaApp.ICABookingApp.config;

import dev.icaApp.ICABookingApp.model.Normal;
import dev.icaApp.ICABookingApp.model.VIP1;
import dev.icaApp.ICABookingApp.model.VIP2;
import dev.icaApp.ICABookingApp.model.VVIP;
import dev.icaApp.ICABookingApp.repostory.NormalRepository;
import dev.icaApp.ICABookingApp.repostory.VIP1Repository;
import dev.icaApp.ICABookingApp.repostory.VIP2Repository;
import dev.icaApp.ICABookingApp.repostory.VVIPRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SeedDatabase {
    private final VIP1Repository vip1Repository;
    private final VIP2Repository vip2Repository;
    private final VVIPRepository vvipRepository;
    private final NormalRepository normalRepository;


    @PostConstruct
    public void init(){
        List <VIP1> vips = vip1Repository.findAll();
        if(vips.size()<1){
            Integer VVIP = 80;
            Integer VIP1 = 80;
            Integer VIP2 = 80;
            Integer Normal = 600;

            //VVIP SEEDING
            for(int i =1;i<=VVIP;i++){
                VVIP vvip = new VVIP();
                vvip.setSeatNo(i);
                vvip.setStatus(0);
                vvipRepository.save(vvip);
            }

            //VIP1 SEEDING
            for(int i =1;i<=VIP1;i++){
                VIP1 vip1 = new VIP1();
                vip1.setSeatNo(i);
                vip1.setStatus(0);
                vip1Repository.save(vip1);
            }

            //VIP2 SEEDING
            for(int i =1;i<=VIP2;i++){
                VIP2 vip2 = new VIP2();
                vip2.setSeatNo(i);
                vip2.setStatus(0);
                vip2Repository.save(vip2);
            }

            //NORMAL SEEDING
            for(int i =1;i<=Normal;i++){
                Normal normal = new Normal();
                normal.setSeatNo(i);
                normal.setStatus(0);
                normalRepository.save(normal);
            }
        }
    }
}
