package ro.rentamotorcycle.rentamotorcycle.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RatingDto {

    private int id;
    private int userId;
    private int motorcycleId;
    private int rating;
    private Date createdAt;
    private Date updatedAt;


}
