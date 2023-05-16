package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.repositories.LocationRepository;
import ro.rentamotorcycle.rentamotorcycle.service.LocationService;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    @Override
    public LocationEntity createLocation(LocationEntity locationEntity) {
        return locationRepository.save(locationEntity);
    }

    @Override
    public LocationEntity getLocationById(Integer id) {
        return locationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + id));
    }

    @Override
    public LocationEntity updateLocation(int id, LocationEntity locationEntity) {
        Optional<LocationEntity> optionalLocation = locationRepository.findById(id);
        if(optionalLocation.isPresent()){
            LocationEntity existingLocation = optionalLocation.get();
            existingLocation.setName(locationEntity.getName());
            existingLocation.setAddress(locationEntity.getAddress());
            existingLocation.setPhoneNumber(locationEntity.getPhoneNumber());
            return locationRepository.save(existingLocation);
        }else{
            return null;
        }
    }

    @Override
    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);

    }

    @Override
    public List<LocationEntity> getLocations() {
        return locationRepository.findAll();
    }
}
