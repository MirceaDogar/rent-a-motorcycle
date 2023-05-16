package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleAccessoryDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleAccessoryEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.MotorcycleAccessoryMapper;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleAccessoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/motorcycle-accessories")
public class MotorcycleAccessoryController {
    @Autowired
    private final MotorcycleAccessoryService motorcycleAccessoryService;
    @Autowired
    private final MotorcycleAccessoryMapper motorcycleAccessoryMapper;

    @Autowired
    public MotorcycleAccessoryController(MotorcycleAccessoryService motorcycleAccessoryService, MotorcycleAccessoryMapper motorcycleAccessoryMapper) {
        this.motorcycleAccessoryService = motorcycleAccessoryService;
        this.motorcycleAccessoryMapper = motorcycleAccessoryMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a motorcycle accessory.")
    public MotorcycleAccessoryDto createMotorcycleAccessory(@RequestBody MotorcycleAccessoryDto motorcycleAccessoryDto) {
        MotorcycleAccessoryEntity motorcycleAccessoryEntity = motorcycleAccessoryMapper.toEntity(motorcycleAccessoryDto);
        MotorcycleAccessoryEntity savedMotorcycleAccessoryEntity = motorcycleAccessoryService.createMotorcycleAccessory(motorcycleAccessoryEntity);
        return motorcycleAccessoryMapper.toDto(savedMotorcycleAccessoryEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get motorcycle accessory by Id.")
    public MotorcycleAccessoryDto getMotorcycleAccessoryById(@PathVariable int id) {
        MotorcycleAccessoryEntity motorcycleAccessoryEntity = motorcycleAccessoryService.getMotorcycleAccessoryById(id);
        return motorcycleAccessoryMapper.toDto(motorcycleAccessoryEntity);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update motorcycle accessory.")
    public MotorcycleAccessoryDto updateMotorcycleAccessory(@PathVariable int id, @RequestBody MotorcycleAccessoryDto motorcycleAccessoryDto) {
        MotorcycleAccessoryEntity motorcycleAccessoryEntity = motorcycleAccessoryMapper.toEntity(motorcycleAccessoryDto);
        MotorcycleAccessoryEntity updatedMotorcycleAccessoryEntity = motorcycleAccessoryService.updateMotorcycleAccessory(id, motorcycleAccessoryEntity);
        return motorcycleAccessoryMapper.toDto(updatedMotorcycleAccessoryEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete motorcycle accessory")
    public void deleteMotorcycleAccessory(@PathVariable int id) {
        motorcycleAccessoryService.deleteMotorcycleAccessory(id);
    }

    @GetMapping
    @ApiOperation(value = "Get all motorcycle accessories.")
    public List<MotorcycleAccessoryDto> getAllMotorcycleAccessories() {
        List<MotorcycleAccessoryEntity> motorcycleAccessoryEntities = motorcycleAccessoryService.getMotorcycleAccessories();
        return motorcycleAccessoryEntities.stream().map(motorcycleAccessoryMapper::toDto).collect(Collectors.toList());
    }
}