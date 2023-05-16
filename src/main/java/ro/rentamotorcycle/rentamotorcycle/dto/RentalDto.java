package ro.rentamotorcycle.rentamotorcycle.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalDto {
    private int id;
    private int userId;
    private int motorcycleId;
    private LocalDate pickupDate;
    private LocalDate dropoffDate;
    private String pickupLocation;
    private String dropoffLocation;
}