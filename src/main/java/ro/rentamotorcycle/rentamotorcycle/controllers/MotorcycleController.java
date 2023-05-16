package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleDetailsDto;
import ro.rentamotorcycle.rentamotorcycle.dto.MotorcycleDto;
import ro.rentamotorcycle.rentamotorcycle.dto.ReservationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.MotorcycleMapper;
import ro.rentamotorcycle.rentamotorcycle.mapper.ReservationMapper;
import ro.rentamotorcycle.rentamotorcycle.service.MotorcycleService;
import ro.rentamotorcycle.rentamotorcycle.service.ReservationService;
import ro.rentamotorcycle.rentamotorcycle.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/motorcycles")
public class MotorcycleController {
    @Autowired
    private final MotorcycleService motorcycleService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final ReservationMapper reservationMapper;
    @Autowired
    private final MotorcycleMapper motorcycleMapper;
    @Autowired
    public MotorcycleController(MotorcycleService motorcycleService, UserService userService, ReservationService reservationService, ReservationMapper reservationMapper, MotorcycleMapper motorcycleMapper) {
        this.motorcycleService = motorcycleService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
        this.motorcycleMapper = motorcycleMapper;
    }


    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Create a motorcycle.")
    public MotorcycleDto createMotorcycle(@RequestBody MotorcycleDto motorcycleDto) {
        MotorcycleEntity motorcycleEntity = motorcycleMapper.toEntity(motorcycleDto);
        MotorcycleEntity savedMotorcycleEntity = motorcycleService.createMotorcycle(motorcycleEntity);
        return motorcycleMapper.toDto(savedMotorcycleEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get a motorcycle by Id.")
    public MotorcycleDto getMotorcycleById(@PathVariable int id) {
        MotorcycleEntity motorcycleEntity = motorcycleService.getMotorcycleById(id);
        return motorcycleMapper.toDto(motorcycleEntity);
    }

    @GetMapping
    @ApiOperation(value = "Get all motorcycles.")
    public List<MotorcycleDto> getAllMotorcycles() {
        List<MotorcycleEntity> motorcycleEntities = motorcycleService.getMotorcycles();
        return motorcycleEntities.stream()
                .map(motorcycleMapper::toDto)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Update a motorcycle.")
    public MotorcycleDto updateMotorcycle(@PathVariable int id, @RequestBody MotorcycleDto motorcycleDto) {
        MotorcycleEntity motorcycleEntity = motorcycleMapper.toEntity(motorcycleDto);
        MotorcycleEntity updatedMotorcycleEntity = motorcycleService.updateMotorcycle(id, motorcycleEntity);
        return motorcycleMapper.toDto(updatedMotorcycleEntity);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "Delete a motorcycle.")
    public void deleteMotorcycle(@PathVariable int id) {
        motorcycleService.deleteMotorcycle(id);
    }

    @PostMapping("/{id}/reserve")
    @ApiOperation(value = "Reserve a motorcycle.")
    public ReservationDto reserveMotorcycle(@PathVariable int id, @RequestBody ReservationDto reservationDto) {
        MotorcycleEntity motorcycleEntity = motorcycleService.getMotorcycleById(id);
        UserEntity userEntity = userService.getCurrentUser(); // obtinerea utilizatorului curent

        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDto);
        reservationEntity.setMotorcycle(motorcycleEntity);
        reservationEntity.setUser(userEntity);

        ReservationEntity savedReservationEntity = reservationService.createReservation(reservationEntity);
        return reservationMapper.toDto(savedReservationEntity);
    }
    @GetMapping("/available")
    @ApiOperation(value = "Get all available motorcycles.")
    public List<MotorcycleDto> getAvailableMotorcycles(
            @RequestParam(name = "pickupTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date pickupTime,
            @RequestParam(name = "dropOffTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dropOffTime) {
        List<MotorcycleEntity> availableMotorcycles = motorcycleService.getAvailableMotorcycles(pickupTime, dropOffTime);
        List<MotorcycleDto> result = new ArrayList<>();

        for (MotorcycleEntity motorcycleEntity : availableMotorcycles) {
            MotorcycleDto motorcycleDto = motorcycleMapper.toDto(motorcycleEntity);
            MotorcycleDetailsDto motorcycleDetailsDto = new MotorcycleDetailsDto();
            motorcycleDetailsDto.setColor(motorcycleEntity.getColor());
            motorcycleDetailsDto.setYear(motorcycleEntity.getYear());
            motorcycleDetailsDto.setMake(motorcycleEntity.getMake());
            motorcycleDetailsDto.setModel(motorcycleEntity.getModel());
            motorcycleDetailsDto.setRentalRate(motorcycleEntity.getRentalRate());
            motorcycleDto.setDetails(motorcycleDetailsDto);
            result.add(motorcycleDto);
        }return result;

    }
    @PostMapping("/{id}/ratings")
    @ApiOperation(value = "Add a rating.")
            public ResponseEntity<String> addRating(
            @PathVariable("motorcycle") Integer motorcycle,
            @RequestParam("user") Integer user,
            @RequestParam("rating") Integer rating) {
        userService.addRating(motorcycle, user, rating);
        return ResponseEntity.ok("Rating added successfully!");
    }
    @GetMapping("/filter")
    @ApiOperation(value = "Filter motorcycles by various criteria.")
    public ResponseEntity<List<MotorcycleDto>> filterMotorcycles(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer year) {
        List<MotorcycleDto> filteredMotorcycles = motorcycleService.filterMotorcycles(make, model, color, year);
        return ResponseEntity.ok(filteredMotorcycles);
    }

}
