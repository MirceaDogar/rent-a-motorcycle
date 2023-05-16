package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.AccessoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.AccessoryRepository;

import java.util.Optional;
@Component
public class AccessoryMapper {
    @Autowired
    private final AccessoryRepository accessoryRepository;
    @Autowired
    public AccessoryMapper(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public AccessoryEntity toEntity(AccessoryDto dto) {
        AccessoryEntity entity = new AccessoryEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRentalRate(dto.getRentalRate());
        return entity;
    }

    public AccessoryDto toDto(AccessoryEntity entity) {
        AccessoryDto dto = new AccessoryDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRentalRate(entity.getRentalRate());
        return dto;
    }

    public AccessoryEntity toEntity(AccessoryDto dto, AccessoryEntity entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setRentalRate(dto.getRentalRate());
        return entity;
    }

    public AccessoryDto findById(int id) {
        Optional<AccessoryEntity> entity = accessoryRepository.findById(id);
        return entity.map(this::toDto).orElse(null);
    }
}
