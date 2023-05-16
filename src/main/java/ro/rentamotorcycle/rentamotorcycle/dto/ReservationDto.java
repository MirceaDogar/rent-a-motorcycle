package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {

    private int id;
    private int userId;
    private int motorcycleId;
    private Date pickUpTime;
    private Date dropOffTime;

}
