package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.AccessoryEntity;


import java.util.Optional;
@NonNullApi
@Repository
public interface AccessoryRepository extends JpaRepository<AccessoryEntity, Integer> {
    Optional<AccessoryEntity> findById(Integer id);
}
