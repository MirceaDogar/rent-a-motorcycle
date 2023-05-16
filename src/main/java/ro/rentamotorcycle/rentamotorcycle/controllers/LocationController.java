package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.LocationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.LocationEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.LocationMapper;
import ro.rentamotorcycle.rentamotorcycle.service.LocationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private final LocationService locationService;
    @Autowired
    private final LocationMapper locationMapper;

    @Autowired
    public LocationController(LocationService locationService, LocationMapper locationMapper) {
        this.locationService = locationService;
        this.locationMapper = locationMapper;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a location.")
    public LocationDto createLocation(@RequestBody LocationDto locationDto) {
        LocationEntity locationEntity = locationMapper.toEntity(locationDto);
        LocationEntity savedLocationEntity = locationService.createLocation(locationEntity);
        return locationMapper.toDto(savedLocationEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get location by id")
    public LocationDto getLocationById(@PathVariable int id) {
        LocationEntity locationEntity = locationService.getLocationById(id);
        return locationMapper.toDto(locationEntity);
    }

    @GetMapping
    @ApiOperation(value = "Get all locations.")
    public List<LocationDto> getAllLocations() {
        List<LocationEntity> locationEntities = locationService.getLocations();
        return locationEntities.stream()
                .map(locationMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update a location")
    public LocationDto updateLocation(@PathVariable int id, @RequestBody LocationDto locationDto) {
        LocationEntity locationEntity = locationMapper.toEntity(locationDto);
        LocationEntity updatedLocationEntity = locationService.updateLocation(id, locationEntity);
        return locationMapper.toDto(updatedLocationEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete a location")
    public void deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
    }
}