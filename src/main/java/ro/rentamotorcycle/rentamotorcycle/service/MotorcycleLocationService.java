package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleLocationEntity;

import java.util.List;

public interface MotorcycleLocationService {
    MotorcycleLocationEntity createMotorcycleLocation(MotorcycleLocationEntity motorcycleLocationEntity);
    MotorcycleLocationEntity getMotorcycleLocationById(Integer id);
    MotorcycleLocationEntity updateMotorcycleLocation(int id, MotorcycleLocationEntity motorcycleLocationEntity);
    void deleteMotorcycleLocation(Integer id);
    List<MotorcycleLocationEntity> getMotorcycleLocations();
}
