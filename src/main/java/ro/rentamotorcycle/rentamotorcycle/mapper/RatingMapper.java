package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.RatingDto;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;

@Component
public class RatingMapper {

    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final MotorcycleMapper motorcycleMapper;

    @Autowired
    public RatingMapper(UserMapper userMapper, MotorcycleMapper motorcycleMapper) {
        this.userMapper = userMapper;
        this.motorcycleMapper = motorcycleMapper;
    }

    public RatingDto toDto(RatingEntity ratingEntity) {
        RatingDto ratingDto = new RatingDto();
        ratingDto.setId(ratingEntity.getId());
        ratingDto.setUserId(ratingEntity.getUser().getId());
        ratingDto.setMotorcycleId(ratingEntity.getMotorcycle().getId());
        ratingDto.setRating(ratingEntity.getRating());
        ratingDto.setCreatedAt(ratingEntity.getCreatedAt());
        ratingDto.setUpdatedAt(ratingEntity.getUpdatedAt());
        return ratingDto;
    }

    public RatingEntity toEntity(RatingDto ratingDto) {
        RatingEntity ratingEntity = new RatingEntity();
        ratingEntity.setId(ratingDto.getId());
        ratingEntity.setUser(userMapper.toEntity(userMapper.findById(ratingDto.getUserId())));
        ratingEntity.setMotorcycle(motorcycleMapper.toEntity(motorcycleMapper.findById(ratingDto.getMotorcycleId())));
        ratingEntity.setRating(ratingDto.getRating());
        ratingEntity.setCreatedAt(ratingDto.getCreatedAt());
        ratingEntity.setUpdatedAt(ratingDto.getUpdatedAt());
        return ratingEntity;
    }
}