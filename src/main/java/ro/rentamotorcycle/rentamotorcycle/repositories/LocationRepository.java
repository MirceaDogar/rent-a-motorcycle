package ro.rentamotorcycle.rentamotorcycle.repositories;


import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;

import java.util.Optional;
@NonNullApi
@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Integer> {
    Optional<LocationEntity> findById(Integer id);
}
