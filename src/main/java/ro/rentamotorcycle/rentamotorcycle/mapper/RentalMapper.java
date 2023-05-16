package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.RentalDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.RentalEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;

import java.util.Optional;

@Component
public class RentalMapper {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public RentalMapper(UserRepository userRepository, MotorcycleRepository motorcycleRepository) {
        this.userRepository = userRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public RentalEntity toEntity(RentalDto dto) {
        RentalEntity entity = new RentalEntity();
        entity.setId(dto.getId());
        entity.setPickupDate(dto.getPickupDate());
        entity.setDropoffDate(dto.getDropoffDate());

        Optional<UserEntity> optionalUserEntity = userRepository.findById(dto.getUserId());
        optionalUserEntity.ifPresent(entity::setUser);

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        return entity;
    }

    public RentalDto toDto(RentalEntity entity) {
        RentalDto dto = new RentalDto();
        dto.setId(entity.getId());
        dto.setPickupDate(entity.getPickupDate());
        dto.setDropoffDate(entity.getDropoffDate());
        dto.setUserId(entity.getUser().getId());
        dto.setMotorcycleId(entity.getMotorcycle().getId());
        dto.setPickupLocation(entity.getPickupLocation());
        dto.setDropoffLocation(entity.getDropoffLocation());
        return dto;
    }

    public RentalEntity toEntity(RentalDto dto, RentalEntity entity) {
        entity.setId(dto.getId());
        entity.setPickupDate(dto.getPickupDate());
        entity.setDropoffDate(dto.getDropoffDate());

        Optional<UserEntity> optionalUserEntity = userRepository.findById(dto.getUserId());
        optionalUserEntity.ifPresent(entity::setUser);

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        return entity;
    }
}