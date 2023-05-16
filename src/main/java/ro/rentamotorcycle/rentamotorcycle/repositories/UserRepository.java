package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;

import java.util.Optional;
@Repository
@NonNullApi
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findById(int id);
    Optional<UserEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}
