package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.repositories.AccessoryRepository;
import ro.rentamotorcycle.rentamotorcycle.service.AccessoryService;

import java.util.List;
import java.util.Optional;
@Service
public class AccessoryServiceImpl implements AccessoryService {
    @Autowired
    private final AccessoryRepository accessoryRepository;

    @Autowired
    public AccessoryServiceImpl(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    @Override
    public AccessoryEntity createAccessory(AccessoryEntity accessoryEntity) {
        return accessoryRepository.save(accessoryEntity);
    }

    @Override
    public AccessoryEntity getAccessoryById(Integer id) {
        return accessoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Accessory not found with id " + id));
    }

    @Override
    public AccessoryEntity updateAccessory(int id, AccessoryEntity accessoryEntity) {
        Optional<AccessoryEntity> optionalAccessory = accessoryRepository.findById(id);
        if(optionalAccessory.isPresent()){
            AccessoryEntity existingAccessory = optionalAccessory.get();
            existingAccessory.setName(accessoryEntity.getName());
            return accessoryRepository.save(existingAccessory);
        }else{
            return null;
        }
    }

    @Override
    public void deleteAccessory(Integer id) {
        accessoryRepository.deleteById(id);
    }

    @Override
    public List<AccessoryEntity> getAccessories() {
        return accessoryRepository.findAll();
    }

}
