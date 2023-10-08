package dev.icaApp.ICABookingApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor

@Builder
@Data
public class SaveAttendeeResponseDto {
    private String name;
    private String phoneNumber;
    private String email;
    private Integer ICACardNumber;
    private List<String> ticketsCategory;
    private List <Integer> seatsNo;
    private Integer ticketId;
    private String status;
    private double totalCost;

    public void addTicket(String s){
        ticketsCategory.add(s);
    }
    public  void  addSeats(Integer num){
        seatsNo.add(num);
    }

    public SaveAttendeeResponseDto(){
        this.ticketsCategory=new ArrayList<>();
        this.seatsNo=new ArrayList<>();
    }

}
