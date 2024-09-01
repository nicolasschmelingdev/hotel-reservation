package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.exception.ReservationNotFoundException;
import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public List<Reservation> searchReservations(Long customerId, Long roomId, String status) {
        return reservationRepository.findReservationsByCustomCriteria(customerId, roomId, status);
    }

    @Transactional(readOnly = true)
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Transactional
    public Reservation updateReservation(Long id, Reservation reservationDetails) {
        Reservation reservation = getReservationById(id);
        reservation.setCheckInDate(reservationDetails.getCheckInDate());
        reservation.setCheckOutDate(reservationDetails.getCheckOutDate());
        reservation.setStatus(reservationDetails.getStatus());
        return reservationRepository.save(reservation);
    }

    @Transactional
    public void deleteReservation(Long id) {
        Reservation reservation = getReservationById(id);
        reservationRepository.delete(reservation);
    }
}
