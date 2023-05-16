package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleAccessoryEntity;

import java.util.Optional;
@NonNullApi
@Repository
public interface MotorcycleAccessoryRepository extends JpaRepository<MotorcycleAccessoryEntity, Integer> {
    Optional<MotorcycleAccessoryEntity> findById(Integer id);
}
