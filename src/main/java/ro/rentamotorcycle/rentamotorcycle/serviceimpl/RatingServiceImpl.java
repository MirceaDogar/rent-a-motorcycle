package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.repositories.RatingRepository;
import ro.rentamotorcycle.rentamotorcycle.service.RatingService;

import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private final RatingRepository ratingRepository;
    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }


    @Override
    public RatingEntity createRating(RatingEntity ratingEntity) {
        return ratingRepository.save(ratingEntity);
    }

    @Override
    public RatingEntity getRatingById(Integer id) {
        return ratingRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rating not found with id " + id));
    }

    @Override
    public RatingEntity updateRating(int id, RatingEntity ratingEntity) {
        Optional<RatingEntity> optionalRating = ratingRepository.findById(id);
        if(optionalRating.isPresent()){
            RatingEntity existingRating = optionalRating.get();
            existingRating.setRating(ratingEntity.getRating());
            return ratingRepository.save(existingRating);
        }else{
            return null;
        }
    }

    @Override
    public void deleteRating(Integer id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public List<RatingEntity> getAllRatings() {
        return (List<RatingEntity>) ratingRepository.findAll();
    }
}
