package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.RentalDto;
import ro.rentamotorcycle.rentamotorcycle.entities.RentalEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.RentalMapper;
import ro.rentamotorcycle.rentamotorcycle.service.RentalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    @Autowired
    private final RentalService rentalService;
    @Autowired
    private final RentalMapper rentalMapper;

    @Autowired
    public RentalController(RentalService rentalService, RentalMapper rentalMapper) {
        this.rentalService = rentalService;
        this.rentalMapper = rentalMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create a rental.")
    public RentalDto createRental(@RequestBody RentalDto rentalDto) {
        RentalEntity rentalEntity = rentalMapper.toEntity(rentalDto);
        RentalEntity savedRentalEntity = rentalService.createRental(rentalEntity);
        return rentalMapper.toDto(savedRentalEntity);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Get rental by Id")
    public RentalDto getRentalById(@PathVariable int id) {
        RentalEntity rentalEntity = rentalService.getRentalById(id);
        return rentalMapper.toDto(rentalEntity);
    }

    @GetMapping
    @ApiOperation(value = "Get all rentals.")
    public List<RentalDto> getAllRentals() {
        List<RentalEntity> rentalEntities = rentalService.getRentals();
        return rentalEntities.stream()
                .map(rentalMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update a rental.")
    public RentalDto updateRental(@PathVariable int id, @RequestBody RentalDto rentalDto) {
        RentalEntity rentalEntity = rentalMapper.toEntity(rentalDto);
        RentalEntity updatedRentalEntity = rentalService.updateRental(id, rentalEntity);
        return rentalMapper.toDto(updatedRentalEntity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a rental.")
    public void deleteRental(@PathVariable int id) {
        rentalService.deleteRental(id);
    }
    @PostMapping("/locations")
    @ApiOperation(value = "Create a rental with location")
    public RentalDto createRentalWithLocations(@RequestBody RentalDto rentalDto) {
        return rentalService.createRentalWithLocations(rentalDto);
    }


}
