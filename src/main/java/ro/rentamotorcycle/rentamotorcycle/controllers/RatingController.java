package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.RatingDto;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.RatingMapper;
import ro.rentamotorcycle.rentamotorcycle.service.RatingService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private final RatingService ratingService;
    @Autowired
    private final RatingMapper ratingMapper;

    @Autowired
    public RatingController(RatingService ratingService, RatingMapper ratingMapper) {
        this.ratingService = ratingService;
        this.ratingMapper = ratingMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create rating.")
    public RatingDto createRating(@RequestBody RatingDto ratingDto) {
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingDto);
        RatingEntity savedRatingEntity = ratingService.createRating(ratingEntity);
        return ratingMapper.toDto(savedRatingEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get rating by Id.")
    public RatingDto getRatingById(@PathVariable int id) {
        RatingEntity ratingEntity = ratingService.getRatingById(id);
        return ratingMapper.toDto(ratingEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update rating.")
    public RatingDto updateRating(@PathVariable int id, @RequestBody RatingDto ratingDto) {
        RatingEntity ratingEntity = ratingMapper.toEntity(ratingDto);
        RatingEntity updatedRatingEntity = ratingService.updateRating(id, ratingEntity);
        return ratingMapper.toDto(updatedRatingEntity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete rating.")
    public void deleteRating(@PathVariable int id) {
        ratingService.deleteRating(id);
    }

}