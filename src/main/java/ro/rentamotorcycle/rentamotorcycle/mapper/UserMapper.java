package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.UserDto;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;

import java.util.Optional;

@Component
public class UserMapper {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setFullName(entity.getFullName());
        dto.setPhoneNumber(entity.getPhoneNumber());
        dto.setEmail(entity.getEmail());
        return dto;
    }

    public UserEntity toEntity(UserDto dto, UserEntity entity) {
        entity.setId(dto.getId());
        entity.setFullName(dto.getFullName());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    public UserDto findById(int id) {
        Optional<UserEntity> optional = userRepository.findById(id);
        return optional.map(this::toDto).orElse(null);
    }
}