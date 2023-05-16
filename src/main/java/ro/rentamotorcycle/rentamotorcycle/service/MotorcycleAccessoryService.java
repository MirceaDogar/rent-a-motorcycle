package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleAccessoryEntity;

import java.util.List;

public interface MotorcycleAccessoryService {
    MotorcycleAccessoryEntity createMotorcycleAccessory(MotorcycleAccessoryEntity motorcycleAccessoryEntity);
    MotorcycleAccessoryEntity getMotorcycleAccessoryById(Integer id);
    MotorcycleAccessoryEntity updateMotorcycleAccessory(int id, MotorcycleAccessoryEntity motorcycleAccessoryEntity);
    void deleteMotorcycleAccessory(Integer id);
    List<MotorcycleAccessoryEntity> getMotorcycleAccessories();
}