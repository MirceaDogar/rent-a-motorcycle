package ro.rentamotorcycle.rentamotorcycle.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ro.rentamotorcycle.rentamotorcycle.dto.ReservationDto;
import ro.rentamotorcycle.rentamotorcycle.dto.UserDto;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.mapper.ReservationMapper;
import ro.rentamotorcycle.rentamotorcycle.mapper.UserMapper;
import ro.rentamotorcycle.rentamotorcycle.repositories.ReservationRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;
import ro.rentamotorcycle.rentamotorcycle.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private final UserService userService;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final ReservationMapper reservationMapper;
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    public UserController(UserService userService, UserMapper userMapper,ReservationRepository reservationRepository, ReservationMapper reservationMapper,UserRepository userRepository) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.reservationMapper = reservationMapper;
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    @PostMapping
    @ApiOperation(value = "Create a new user")
    @PreAuthorize("hasRole('ADMIN')")
    public UserDto createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        UserEntity savedUserEntity = userService.createUser(userEntity);
        return userMapper.toDto(savedUserEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user by ID")
    public UserDto getUserById(@PathVariable int id) {
        UserEntity userEntity = userService.getUserById(id);
        return userMapper.toDto(userEntity);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update user by ID")
    public UserDto updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        UserEntity userEntity = userMapper.toEntity(userDto);
        UserEntity updatedUserEntity = userService.updateUser(id, userEntity);
        return userMapper.toDto(updatedUserEntity);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete user by ID")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
    }
    @GetMapping("/users/{id}/reservations")
    @ApiOperation(value = "Get reservations for user by ID")
    public List<ReservationDto> getReservationsForUser(@PathVariable("id") Integer userId) {
        List<ReservationEntity> reservations = reservationRepository.findAllByUserId(userId);
        return reservationMapper.toDtoList(reservations);
    }

}