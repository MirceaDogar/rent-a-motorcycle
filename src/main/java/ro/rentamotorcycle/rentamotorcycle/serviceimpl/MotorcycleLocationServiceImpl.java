package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleLocationEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleLocationRepository;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleLocationService;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleLocationServiceImpl implements MotorcycleLocationService {
    @Autowired
    private final MotorcycleLocationRepository motorcycleLocationRepository;

    @Autowired
    public MotorcycleLocationServiceImpl(MotorcycleLocationRepository motorcycleLocationRepository) {
        this.motorcycleLocationRepository = motorcycleLocationRepository;
    }

    @Override
    public MotorcycleLocationEntity createMotorcycleLocation(MotorcycleLocationEntity motorcycleLocationEntity) {
        return motorcycleLocationRepository.save(motorcycleLocationEntity);
    }

    @Override
    public MotorcycleLocationEntity getMotorcycleLocationById(Integer id) {
        return motorcycleLocationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorcycle Location not found with id " + id));
    }

    @Override
    public MotorcycleLocationEntity updateMotorcycleLocation(int id, MotorcycleLocationEntity motorcycleLocationEntity) {
        Optional<MotorcycleLocationEntity> optionalMotorcycleLocation = motorcycleLocationRepository.findById(id);
        if (optionalMotorcycleLocation.isPresent()) {
            MotorcycleLocationEntity existingMotorcycleLocation = optionalMotorcycleLocation.get();
            existingMotorcycleLocation.setLocation(motorcycleLocationEntity.getLocation());
            existingMotorcycleLocation.setMotorcycle(motorcycleLocationEntity.getMotorcycle());
            return motorcycleLocationRepository.save(existingMotorcycleLocation);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMotorcycleLocation(Integer id) {
        motorcycleLocationRepository.deleteById(id);
    }

    @Override
    public List<MotorcycleLocationEntity> getMotorcycleLocations() {
        return motorcycleLocationRepository.findAll();
    }
}
