package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.dto.SearchHistoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.SearchEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.SearchHistoryMapper;
import ro.rentamotorcycle.rentamotorcycle.repositories.SearchHistoryRepository;
import ro.rentamotorcycle.rentamotorcycle.service.SearchHistoryService;

import java.util.List;
@Service
public class SearchHistoryServiceImpl implements SearchHistoryService {
    @Autowired
    private final SearchHistoryRepository searchHistoryRepository;
    @Autowired
    private final SearchHistoryMapper searchHistoryMapper;
    @Autowired
    public SearchHistoryServiceImpl(SearchHistoryRepository searchHistoryRepository, SearchHistoryMapper searchHistoryMapper) {
        this.searchHistoryRepository = searchHistoryRepository;
        this.searchHistoryMapper = searchHistoryMapper;


    }
    @Override
    public SearchHistoryDto saveSearchHistory(SearchHistoryDto searchHistoryDto) {
            SearchEntity searchEntity = searchHistoryMapper.toEntity(searchHistoryDto);
            SearchEntity savedSearchEntity = searchHistoryRepository.save(searchEntity);
            return searchHistoryMapper.toDto(savedSearchEntity);
    }

    @Override
    public List<SearchHistoryDto> getSearchHistoryByUserId(int userId) {
        List<SearchEntity> searchHistoryEntities = searchHistoryRepository.findByUserId(userId);
        return searchHistoryMapper.toDtoList(searchHistoryEntities);
    }

    @Override
    public void deleteSearchHistoryByUserId(int userId) {
        searchHistoryRepository.deleteByUserId(userId);

    }

}
