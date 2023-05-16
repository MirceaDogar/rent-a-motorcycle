package ro.rentamotorcycle.rentamotorcycle.mapper;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.ReservationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationMapper {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MotorcycleRepository motorcycleRepository;

    @Autowired
    public ReservationMapper(UserRepository userRepository, MotorcycleRepository motorcycleRepository) {
        this.userRepository = userRepository;
        this.motorcycleRepository = motorcycleRepository;
    }

    public ReservationEntity toEntity(ReservationDto dto) {
        ReservationEntity entity = new ReservationEntity();
        entity.setId(dto.getId());
        entity.setPickUpTime(dto.getPickUpTime());
        entity.setDropOffTime(dto.getDropOffTime());

        Optional<UserEntity> optionalUserEntity = userRepository.findById(dto.getUserId());
        optionalUserEntity.ifPresent(entity::setUser);

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        return entity;
    }

    public ReservationDto toDto(ReservationEntity entity) {
        ReservationDto dto = new ReservationDto();
        dto.setId(entity.getId());
        dto.setPickUpTime(entity.getPickUpTime());
        dto.setDropOffTime(entity.getDropOffTime());
        dto.setUserId(entity.getUser().getId());
        dto.setMotorcycleId(entity.getMotorcycle().getId());
        return dto;
    }

    public ReservationEntity toEntity(ReservationDto dto, ReservationEntity entity) {
        entity.setId(dto.getId());
        entity.setPickUpTime(dto.getPickUpTime());
        entity.setDropOffTime(dto.getDropOffTime());

        Optional<UserEntity> optionalUserEntity = userRepository.findById(dto.getUserId());
        optionalUserEntity.ifPresent(entity::setUser);

        Optional<MotorcycleEntity> optionalMotorcycleEntity = motorcycleRepository.findById(dto.getMotorcycleId());
        optionalMotorcycleEntity.ifPresent(entity::setMotorcycle);

        return entity;
    }
    public List<ReservationDto> toDtoList(List<ReservationEntity> reservationEntities) {
        return reservationEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}