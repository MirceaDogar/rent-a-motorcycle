package ro.rentamotorcycle.rentamotorcycle.mapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class MotorcycleMapper {
    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    @Autowired
    public MotorcycleMapper(MotorcycleRepository motorcycleRepository) {
        this.motorcycleRepository = motorcycleRepository;
    }

    public MotorcycleEntity toEntity(MotorcycleDto dto) {
        MotorcycleEntity entity = new MotorcycleEntity();
        entity.setId(dto.getId());
        entity.setMake(dto.getMake());
        entity.setModel(dto.getModel());
        entity.setYear(dto.getYear());
        entity.setColor(dto.getColor());
        entity.setRentalRate(dto.getRentalRate());
        entity.setIsAvailable(dto.isAvailable());
        return entity;
    }

    public MotorcycleDto toDto(MotorcycleEntity entity) {
        MotorcycleDto dto = new MotorcycleDto();
        dto.setId(entity.getId());
        dto.setMake(entity.getMake());
        dto.setModel(entity.getModel());
        dto.setYear(entity.getYear());
        dto.setColor(entity.getColor());
        dto.setRentalRate(entity.getRentalRate());
        dto.setAvailable(entity.isAvailable());
        return dto;
    }

    public MotorcycleEntity toEntity(MotorcycleDto dto, MotorcycleEntity entity) {
        entity.setId(dto.getId());
        entity.setMake(dto.getMake());
        entity.setModel(dto.getModel());
        entity.setYear(dto.getYear());
        entity.setColor(dto.getColor());
        entity.setRentalRate(dto.getRentalRate());
        entity.setIsAvailable(dto.isAvailable());
        return entity;
    }

    public MotorcycleDto findById(int id) {
        Optional<MotorcycleEntity> entity = motorcycleRepository.findById(id);
        return entity.map(this::toDto).orElse(null);
    }
    public List<MotorcycleDto> toDtoList(List<MotorcycleEntity> motorcycles) {
        return motorcycles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}