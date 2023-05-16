package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@NonNullApi
@Repository
public interface MotorcycleRepository extends JpaRepository<MotorcycleEntity, Integer> {
    Optional<MotorcycleEntity> findById(Integer id);
    @Query("SELECT m FROM MotorcycleEntity m WHERE m.isAvailable = true AND m.id NOT IN " +
            "(SELECT r.motorcycle.id FROM ReservationEntity r WHERE (:start <= r.dropOffTime) AND (:end >= r.pickUpTime))")
    List<MotorcycleEntity> findAllByNotReserved(@Param("start") Date start, @Param("end") Date end);
    List<MotorcycleEntity> findByMakeAndModelAndColorAndYear(String make, String model, String color, int year);
    List<MotorcycleEntity> findByMakeAndModelAndYear(String make, String model, int year);
    List<MotorcycleEntity> findByMakeAndColorAndYear(String make, String color, int year);
    List<MotorcycleEntity> findByYear(int year);
}
