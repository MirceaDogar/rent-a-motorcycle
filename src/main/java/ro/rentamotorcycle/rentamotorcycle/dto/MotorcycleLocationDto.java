package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MotorcycleLocationDto {
    private int id;
    private int motorcycleId;
    private int locationId;
}
