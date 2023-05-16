package ro.rentamotorcycle.rentamotorcycle.service;

import ro.rentamotorcycle.rentamotorcycle.entities.ReservationEntity;

import java.util.List;

public interface ReservationService {
    ReservationEntity createReservation(ReservationEntity reservationEntity);
    ReservationEntity getReservationById(Integer id);
    ReservationEntity updateReservation(int id, ReservationEntity reservationEntity);
    void deleteReservation(Integer id);
    List<ReservationEntity> getAllReservation();
    ReservationEntity cancelReservation(Integer id);
}
