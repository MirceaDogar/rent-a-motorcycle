package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;

import java.util.List;

public interface AccessoryService {
    AccessoryEntity createAccessory(AccessoryEntity accessoryEntity);
    AccessoryEntity getAccessoryById(Integer id);
    AccessoryEntity updateAccessory(int id, AccessoryEntity accessoryEntity);
    void deleteAccessory(Integer id);
    List<AccessoryEntity> getAccessories();
}
