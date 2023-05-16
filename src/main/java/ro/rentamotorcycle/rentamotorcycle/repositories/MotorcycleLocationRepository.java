package ro.rentamotorcycle.rentamotorcycle.repositories;


import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleLocationEntity;

import java.util.Optional;
@NonNullApi
@Repository
public interface MotorcycleLocationRepository extends JpaRepository<MotorcycleLocationEntity, Integer> {
    Optional<MotorcycleLocationEntity> findById(Integer id);
}
