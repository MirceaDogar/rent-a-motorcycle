package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.ReservationDto;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.ReservationMapper;
import ro.rentamotorcycle.rentamotorcycle.service.ReservationService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final ReservationMapper reservationMapper;

    @Autowired
    public ReservationController(ReservationService reservationService, ReservationMapper reservationMapper) {
        this.reservationService = reservationService;
        this.reservationMapper = reservationMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create reservation")
    public ReservationDto createReservation(@RequestBody ReservationDto reservationDto) {
        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDto);
        ReservationEntity savedReservationEntity = reservationService.createReservation(reservationEntity);
        return reservationMapper.toDto(savedReservationEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get reservation by ID")
    public ReservationDto getReservationById(@PathVariable int id) {
        ReservationEntity reservationEntity = reservationService.getReservationById(id);
        return reservationMapper.toDto(reservationEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update reservation.")
    public ReservationDto updateReservation(@PathVariable int id, @RequestBody ReservationDto reservationDto) {
        ReservationEntity reservationEntity = reservationMapper.toEntity(reservationDto);
        ReservationEntity updatedReservationEntity = reservationService.updateReservation(id, reservationEntity);
        return reservationMapper.toDto(updatedReservationEntity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete reservation.")
    public void deleteReservation(@PathVariable int id) {
        reservationService.deleteReservation(id);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ApiOperation(value = "See all reservations.")
    public List<ReservationDto> getAllReservations() {
        List<ReservationEntity> reservationEntities = reservationService.getAllReservation();
        return reservationEntities.stream().map(reservationMapper::toDto).collect(Collectors.toList());
    }
    @PutMapping("/{reservationId}/cancel")
    @ApiOperation(value = "Cancel reservation.")
    public ResponseEntity<String> cancelReservation(@PathVariable int reservationId) {
        reservationService.cancelReservation(reservationId);
        return ResponseEntity.ok("Reservation canceled successfully");
    }
}