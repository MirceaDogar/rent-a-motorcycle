package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MotorcycleDto {

    private MotorcycleDetailsDto details;

    public MotorcycleDetailsDto getDetails() {
        return details;
    }

    public void setDetails(MotorcycleDetailsDto details) {
        this.details = details;
    }

    private int id;
    private String make;
    private String model;
    private int year;
    private String color;
    private int rentalRate;
    private boolean isAvailable;

}
