package ro.rentamotorcycle.rentamotorcycle.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.rentamotorcycle.rentamotorcycle.entities.MotorcycleEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;
import ro.rentamotorcycle.rentamotorcycle.entities.UserEntity;
import ro.rentamotorcycle.rentamotorcycle.exception.BadRequestException;
import ro.rentamotorcycle.rentamotorcycle.exception.ResourceNotFoundException;
import ro.rentamotorcycle.rentamotorcycle.exception.UnauthorizedException;
import ro.rentamotorcycle.rentamotorcycle.repositories.MotorcycleRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.ReservationRepository;
import ro.rentamotorcycle.rentamotorcycle.repositories.UserRepository;
import ro.rentamotorcycle.rentamotorcycle.service.ReservationService;
import ro.rentamotorcycle.rentamotorcycle.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final MotorcycleRepository motorcycleRepository;
    @Autowired
    private final UserService userService;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,
                                  UserRepository userRepository,
                                  MotorcycleRepository motorcycleRepository, UserService userService) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
        this.motorcycleRepository = motorcycleRepository;
        this.userService = userService;
    }


    @Override
    public ReservationEntity createReservation(ReservationEntity reservationEntity) {

        UserEntity userEntity = userRepository.findById(reservationEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        MotorcycleEntity motorcycleEntity = motorcycleRepository.findById(reservationEntity.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Motorcycle not found"));

        boolean isMotorcycleReserved = reservationRepository.existsByMotorcycle(
                motorcycleEntity,
                reservationEntity.getPickUpTime());
        if (isMotorcycleReserved) {
            throw new BadRequestException("Motorcycle already reserved for this period");
        }

        reservationEntity.setUser(userEntity);
        reservationEntity.setMotorcycle(motorcycleEntity);
        return reservationRepository.save(reservationEntity);
    }

    @Override
    public ReservationEntity getReservationById(Integer id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id " + id));
    }

    @Override
    public ReservationEntity updateReservation(int id, ReservationEntity reservationEntity) {
        Optional<ReservationEntity> optionalReservation = reservationRepository.findById(id);
        if(optionalReservation.isPresent()){
            ReservationEntity existingReservation = optionalReservation.get();
            existingReservation.setPickUpTime(reservationEntity.getPickUpTime());
            existingReservation.setDropOffTime(reservationEntity.getDropOffTime());
            existingReservation.setUser(reservationEntity.getUser());
            existingReservation.setMotorcycle(reservationEntity.getMotorcycle());
            return reservationRepository.save(existingReservation);
        }else{
            return null;
        }
    }

    @Override
    public void deleteReservation(Integer id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationEntity> getAllReservation() {
        return (List<ReservationEntity>) reservationRepository.findAll();
    }

    @Override
    public ReservationEntity cancelReservation(Integer id) {
        ReservationEntity reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found"));
        UserEntity currentUser = userService.getCurrentUser();
        if (!reservation.getUser().equals(currentUser)) {
            throw new UnauthorizedException("You are not authorized to cancel this reservation");
        }
        if (reservation.getPickUpTime().compareTo(new Date()) <= 0) {
            throw new BadRequestException("Cannot cancel reservation that has already started");
        }
        reservationRepository.delete(reservation);
        return reservation;
    }
}
