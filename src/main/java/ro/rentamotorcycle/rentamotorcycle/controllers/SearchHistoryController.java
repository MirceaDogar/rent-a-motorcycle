package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.SearchHistoryDto;
import ro.rentamotorcycle.rentamotorcycle.service.SearchHistoryService;

import java.util.List;

@RestController
@RequestMapping("/api/search-history")
public class SearchHistoryController {
    @Autowired
    private final SearchHistoryService searchHistoryService;

    @Autowired
    public SearchHistoryController(SearchHistoryService searchHistoryService) {
        this.searchHistoryService = searchHistoryService;
    }

    @PostMapping("/search-history")
    @ApiOperation(value = "Save history for user by ID")
    public ResponseEntity<SearchHistoryDto> saveSearchHistory(@RequestBody SearchHistoryDto searchHistoryDto) {
        SearchHistoryDto savedSearchHistoryDto = searchHistoryService.saveSearchHistory(searchHistoryDto);
        return ResponseEntity.ok(savedSearchHistoryDto);
    }

    @GetMapping("/search-history/{userId}")
    @ApiOperation(value = "Get history for user by ID")
    public ResponseEntity<List<SearchHistoryDto>> getSearchHistoryByUserId(@PathVariable int userId) {
        List<SearchHistoryDto> searchHistoryDtos = searchHistoryService.getSearchHistoryByUserId(userId);
        return ResponseEntity.ok(searchHistoryDtos);
    }

    @DeleteMapping("/search-history/{userId}")
    @ApiOperation(value = "Delete history for user by ID")
    public ResponseEntity<Void> deleteSearchHistoryByUserId(@PathVariable int userId) {
        searchHistoryService.deleteSearchHistoryByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
