package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;

import java.util.List;

public interface LocationService {
    LocationEntity createLocation(LocationEntity locationEntity);
    LocationEntity getLocationById(Integer id);
    LocationEntity updateLocation(int id, LocationEntity locationEntity);
    void deleteLocation(Integer id);
    List<LocationEntity> getLocations();

}
