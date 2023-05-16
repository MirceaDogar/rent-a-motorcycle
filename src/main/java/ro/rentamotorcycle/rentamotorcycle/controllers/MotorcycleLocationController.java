package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleLocationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleLocationEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.MotorcycleLocationMapper;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleLocationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/motorcycle-locations")
public class MotorcycleLocationController {
    @Autowired
    private final MotorcycleLocationService motorcycleLocationService;
    @Autowired
    private final MotorcycleLocationMapper motorcycleLocationMapper;

    @Autowired
    public MotorcycleLocationController(MotorcycleLocationService motorcycleLocationService, MotorcycleLocationMapper motorcycleLocationMapper) {
        this.motorcycleLocationService = motorcycleLocationService;
        this.motorcycleLocationMapper = motorcycleLocationMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a motorcycle location.")
    public MotorcycleLocationDto createMotorcycleLocation(@RequestBody MotorcycleLocationDto motorcycleLocationDto) {
        MotorcycleLocationEntity motorcycleLocationEntity = motorcycleLocationMapper.toEntity(motorcycleLocationDto);
        MotorcycleLocationEntity savedMotorcycleLocationEntity = motorcycleLocationService.createMotorcycleLocation(motorcycleLocationEntity);
        return motorcycleLocationMapper.toDto(savedMotorcycleLocationEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get motorcycle location by Id.")
    public MotorcycleLocationDto getMotorcycleLocationById(@PathVariable int id) {
        MotorcycleLocationEntity motorcycleLocationEntity = motorcycleLocationService.getMotorcycleLocationById(id);
        return motorcycleLocationMapper.toDto(motorcycleLocationEntity);
    }

    @GetMapping
    @ApiOperation(value = "Get all motorcycle locations.")
    public List<MotorcycleLocationDto> getAllMotorcycleLocations() {
        List<MotorcycleLocationEntity> motorcycleLocationEntities = motorcycleLocationService.getMotorcycleLocations();
        return motorcycleLocationEntities.stream()
                .map(motorcycleLocationMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update a motorcycle location.")
    public MotorcycleLocationDto updateMotorcycleLocation(@PathVariable int id, @RequestBody MotorcycleLocationDto motorcycleLocationDto) {
        MotorcycleLocationEntity motorcycleLocationEntity = motorcycleLocationMapper.toEntity(motorcycleLocationDto);
        MotorcycleLocationEntity updatedMotorcycleLocationEntity = motorcycleLocationService.updateMotorcycleLocation(id, motorcycleLocationEntity);
        return motorcycleLocationMapper.toDto(updatedMotorcycleLocationEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete a motorcycle location.")
    public void deleteMotorcycleLocation(@PathVariable int id) {
        motorcycleLocationService.deleteMotorcycleLocation(id);
    }

}