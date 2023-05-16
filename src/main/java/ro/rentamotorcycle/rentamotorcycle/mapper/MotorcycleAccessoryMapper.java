package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleAccessoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleAccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.AccessoryRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;

import java.util.Optional;

@Component
public class MotorcycleAccessoryMapper {
    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    @Autowired
    private final AccessoryRepository accessoryRepository;
    @Autowired
    public MotorcycleAccessoryMapper(MotorcycleRepository motorcycleRepository, AccessoryRepository accessoryRepository) {
        this.motorcycleRepository = motorcycleRepository;
        this.accessoryRepository = accessoryRepository;
    }

    public MotorcycleAccessoryEntity toEntity(MotorcycleAccessoryDto dto) {
        MotorcycleAccessoryEntity entity = new MotorcycleAccessoryEntity();
        entity.setId(dto.getId());

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        Optional<AccessoryEntity> optionalAccessoryEntity = accessoryRepository.findById(dto.getAccessoryId());
        optionalAccessoryEntity.ifPresent(entity::setAccessory);

        return entity;
    }

    public MotorcycleAccessoryDto toDto(MotorcycleAccessoryEntity entity) {
        MotorcycleAccessoryDto dto = new MotorcycleAccessoryDto();
        dto.setId(entity.getId());
        dto.setMotorcycleId(entity.getMotorcycle().getId());
        dto.setAccessoryId(entity.getAccessory().getId());
        return dto;
    }

    public MotorcycleAccessoryEntity toEntity(MotorcycleAccessoryDto dto, MotorcycleAccessoryEntity entity) {
        entity.setId(dto.getId());

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        Optional<AccessoryEntity> optionalAccessoryEntity = accessoryRepository.findById(dto.getAccessoryId());
        optionalAccessoryEntity.ifPresent(entity::setAccessory);

        return entity;
    }
}