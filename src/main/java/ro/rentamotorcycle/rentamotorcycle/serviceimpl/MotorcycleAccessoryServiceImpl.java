package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleAccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleAccessoryRepository;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleAccessoryService;

import java.util.List;
import java.util.Optional;

@Service
public class MotorcycleAccessoryServiceImpl implements MotorcycleAccessoryService {

    @Autowired
    private final MotorcycleAccessoryRepository motorcycleAccessoryRepository;

    @Autowired
    public MotorcycleAccessoryServiceImpl(MotorcycleAccessoryRepository motorcycleAccessoryRepository) {
        this.motorcycleAccessoryRepository = motorcycleAccessoryRepository;
    }

    @Override
    public MotorcycleAccessoryEntity createMotorcycleAccessory(MotorcycleAccessoryEntity motorcycleAccessoryEntity) {
        return motorcycleAccessoryRepository.save(motorcycleAccessoryEntity);
    }

    @Override
    public MotorcycleAccessoryEntity getMotorcycleAccessoryById(Integer id) {
        return motorcycleAccessoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Motorcycle Accessory not found with id " + id));
    }

    @Override
    public MotorcycleAccessoryEntity updateMotorcycleAccessory(int id, MotorcycleAccessoryEntity motorcycleAccessoryEntity) {
        Optional<MotorcycleAccessoryEntity> optionalMotorcycleAccessory = motorcycleAccessoryRepository.findById(id);
        if (optionalMotorcycleAccessory.isPresent()) {
            MotorcycleAccessoryEntity existingMotorcycleAccessory = optionalMotorcycleAccessory.get();
            existingMotorcycleAccessory.setAccessory(motorcycleAccessoryEntity.getAccessory());
            existingMotorcycleAccessory.setMotorcycle(motorcycleAccessoryEntity.getMotorcycle());
            return motorcycleAccessoryRepository.save(existingMotorcycleAccessory);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMotorcycleAccessory(Integer id) {
        motorcycleAccessoryRepository.deleteById(id);
    }

    @Override
    public List<MotorcycleAccessoryEntity> getMotorcycleAccessories() {
        return motorcycleAccessoryRepository.findAll();
    }
}