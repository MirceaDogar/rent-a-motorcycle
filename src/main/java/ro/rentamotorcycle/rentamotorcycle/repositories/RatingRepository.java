package ro.rentamotorcycle.rentamotorcycle.repositories;


import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.RatingEntity;

import java.util.List;
import java.util.Optional;
@NonNullApi
@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, Integer> {
    Optional<RatingEntity> findById(Integer id);
    List<RatingEntity> findByMotorcycleId(Integer motorcycle);
}
