package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.dto.RentalDto;
import ro.rentamotorcycle.rentamotorcycle.entities.RentalEntity;

import java.util.List;

public interface RentalService {
    RentalEntity createRental(RentalEntity rentalEntity);
    RentalEntity getRentalById(Integer id);
    RentalEntity updateRental(int id, RentalEntity rentalEntity);
    void deleteRental(Integer id);
    List<RentalEntity> getRentals();
    void updateRentalStatus();

    RentalDto createRentalWithLocations(RentalDto rentalDto);
}
