package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.RentalEntity;

import java.util.List;
import java.util.Optional;
@NonNullApi
@Repository
public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {
    Optional<RentalEntity> findById(Integer id);

}
