package ro.rentamotorcycle.rentamotorcycle.mapper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleLocationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleLocationEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.LocationRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;

@Component
public class MotorcycleLocationMapper {
    @Autowired
    private final LocationRepository locationRepository;
    @Autowired
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public MotorcycleLocationMapper(LocationRepository locationRepository, MotorcycleRepository motorcycleRepository) {
        this.locationRepository = locationRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public MotorcycleLocationEntity toEntity(MotorcycleLocationDto dto) {
        MotorcycleLocationEntity entity = new MotorcycleLocationEntity();
        entity.setId(dto.getId());

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        Optional<LocationEntity> optionalLocationEntity = locationRepository.findById(dto.getLocationId());
        optionalLocationEntity.ifPresent(entity::setLocation);

        return entity;
    }

    public MotorcycleLocationDto toDto(MotorcycleLocationEntity entity) {
        MotorcycleLocationDto dto = new MotorcycleLocationDto();
        dto.setId(entity.getId());
        dto.setMotorcycleId(entity.getMotorcycle().getId());
        dto.setLocationId(entity.getLocation().getId());
        return dto;
    }

    public MotorcycleLocationEntity toEntity(MotorcycleLocationDto dto, MotorcycleLocationEntity entity) {
        entity.setId(dto.getId());

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        Optional<LocationEntity> optionalLocationEntity = locationRepository.findById(dto.getLocationId());
        optionalLocationEntity.ifPresent(entity::setLocation);

        return entity;
    }

}