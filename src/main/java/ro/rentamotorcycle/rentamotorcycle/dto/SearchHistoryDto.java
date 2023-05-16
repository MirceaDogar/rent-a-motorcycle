package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchHistoryDto {
    private int id;
    private int userId;
    private int motorcycleId;
    private String location;
    private int year;
    private String make;
    private String model;
}
