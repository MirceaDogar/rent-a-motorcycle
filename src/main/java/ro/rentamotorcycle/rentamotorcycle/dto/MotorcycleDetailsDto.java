package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotorcycleDetailsDto {
    private String make;
    private String model;
    private int year;
    private String color;
    private int rentalRate;
}
