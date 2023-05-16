package ro.rentamotorcycle.rentamotorcycle.mapper;

import org.springframework.stereotype.Component;
import ro.rentamotorcycle.rentamotorcycle.dto.SearchHistoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.SearchEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SearchHistoryMapper {
    public SearchHistoryDto toDto(SearchEntity searchEntity) {
        SearchHistoryDto searchHistoryDto = new SearchHistoryDto();
        searchHistoryDto.setId(searchEntity.getId());
        searchHistoryDto.setUserId(searchEntity.getUser().getId());
        searchHistoryDto.setMotorcycleId(searchEntity.getMotorcycle().getId());
        searchHistoryDto.setLocation(searchEntity.getLocation());
        searchHistoryDto.setYear(searchEntity.getYear());
        searchHistoryDto.setMake(searchEntity.getMake());
        searchHistoryDto.setModel(searchEntity.getModel());
        return searchHistoryDto;
    }

    public SearchEntity toEntity(SearchHistoryDto searchHistoryDto) {
        SearchEntity searchEntity = new SearchEntity();
        searchEntity.setId(searchHistoryDto.getId());
        searchEntity.setLocation(searchHistoryDto.getLocation());
        searchEntity.setYear(searchHistoryDto.getYear());
        searchEntity.setMake(searchHistoryDto.getMake());
        searchEntity.setModel(searchHistoryDto.getModel());
        return searchEntity;
    }
    public List<SearchHistoryDto> toDtoList(List<SearchEntity> searchEntities) {
        return searchEntities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
