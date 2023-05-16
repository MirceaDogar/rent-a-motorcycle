package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.LocationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;

@Component
public class LocationMapper {
    public LocationEntity toEntity(LocationDto dto) {
        LocationEntity entity = new LocationEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }

    public LocationDto toDto(LocationEntity entity) {
        LocationDto dto = new LocationDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAddress(entity.getAddress());
        dto.setPhoneNumber(entity.getPhoneNumber());
        return dto;
    }

    public LocationEntity toEntity(LocationDto dto, LocationEntity entity) {
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        entity.setPhoneNumber(dto.getPhoneNumber());
        return entity;
    }
}