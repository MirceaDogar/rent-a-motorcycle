package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;

import java.util.List;

public interface RatingService {
    RatingEntity createRating(RatingEntity ratingEntity);
    RatingEntity getRatingById(Integer id);
    RatingEntity updateRating(int id, RatingEntity ratingEntity);
    void deleteRating(Integer id);
    List<RatingEntity> getAllRatings();
}
