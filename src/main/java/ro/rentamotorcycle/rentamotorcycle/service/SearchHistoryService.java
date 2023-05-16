package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.dto.SearchHistoryDto;

import java.util.List;

public interface SearchHistoryService {
    SearchHistoryDto saveSearchHistory(SearchHistoryDto searchHistoryDto);

    List<SearchHistoryDto> getSearchHistoryByUserId(int userId);

    void deleteSearchHistoryByUserId(int userId);
}
