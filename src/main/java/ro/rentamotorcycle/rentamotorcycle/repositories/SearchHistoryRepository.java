package ro.rentamotorcycle.rentamotorcycle.repositories;

import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import ro.rentamotorcycle.rentamotorcycle.entities.SearchEntity;

import java.util.List;

@NonNullApi
public interface SearchHistoryRepository extends JpaRepository<SearchEntity, Integer> {
    List<SearchEntity> findByUserId(int userId);
    List<SearchEntity> deleteByUserId(int userId);
}
