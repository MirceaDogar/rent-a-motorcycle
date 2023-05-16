package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;

import java.util.Date;
import java.util.List;

public interface MotorcycleService {

    MotorcycleEntity createMotorcycle(MotorcycleEntity motorcycleEntity);
    MotorcycleEntity getMotorcycleById(Integer id);
    MotorcycleEntity updateMotorcycle(int id, MotorcycleEntity motorcycleEntity);
    void deleteMotorcycle(Integer id);
    List<MotorcycleEntity> getMotorcycles();
    List<MotorcycleEntity> getAvailableMotorcycles(Date pickupTime, Date dropOffTime);
    double getAverageRating(Integer motorcycle);
    List<MotorcycleDto> filterMotorcycles(String make, String model, String color, int year);



}
