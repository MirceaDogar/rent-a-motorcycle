package ro.rentamotorcycle.rentamotorcycle.repositories;


import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@NonNullApi
@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Integer> {
    Optional<ReservationEntity> findById(Integer id);

    @Query("SELECT COUNT(*) > 0 FROM ReservationEntity r WHERE r.motorcycle = :motorcycle AND r.pickUpTime = :pickUpTime")
    boolean existsByMotorcycle(@Param("motorcycle") MotorcycleEntity motorcycleEntity, @Param("pickUpTime") Date pickUpTime);
    List<ReservationEntity> findAllByUserId(int userId);

}
