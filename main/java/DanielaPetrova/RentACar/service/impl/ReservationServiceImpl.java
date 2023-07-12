package DanielaPetrova.RentACar.service.impl;

import DanielaPetrova.RentACar.converter.ReservationConverter;
import DanielaPetrova.RentACar.dto.ReservationRequest;
import DanielaPetrova.RentACar.dto.ReservationResponse;
import DanielaPetrova.RentACar.entity.Reservation;
import DanielaPetrova.RentACar.exception.NotFoundException;
import DanielaPetrova.RentACar.repository.ReservationRepository;
import DanielaPetrova.RentACar.service.ReservationService;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.Set;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ReservationConverter reservationConverter;
    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationConverter reservationConverter, ReservationRepository reservationRepository) {
        this.reservationConverter = reservationConverter;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation bookReservation(ReservationRequest request) {
        Reservation reservation = reservationConverter.toReservation(request);
        return reservationRepository.save(reservation);
    }

    @Override
    public ReservationResponse getById(Long id) {
        return reservationConverter.toReservationResponse(reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation not found")
        ));
    }

    @Override
    public Set<Reservation> getByUser(Long userId) {

        return reservationRepository.findAllByUser(userId);
    }

    @Override
    public Set<Reservation> getByCar(Long carId) {
        return reservationRepository.findAllByCar(carId);
    }

    @Override
    public Set<Reservation> getByPeriod(String startDate, String endDate) {
        Set<Reservation> reservationSet = reservationRepository.getReservationsByPeriod(
                startDate, endDate)
                .orElse(Collections.emptySet());
         return reservationSet;
    }

    @Override
    public ReservationResponse updateReservation(Long id, ReservationRequest updateRequest) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation not found")
        );;
        if(updateRequest.getStartDate() != null){
            reservation.setStartDate(updateRequest.getStartDate());
        }
        if(updateRequest.getEndDate() != null){
            reservation.setEndDate(updateRequest.getEndDate());
        }
        if(updateRequest.getCarId() != null){
            reservation.setCar(reservation.getCar());
        }
        if(updateRequest.getUserId() != null){
            reservation.setUser(reservation.getUser());
        }
        return reservationConverter.toReservationResponse(reservationRepository.save(reservation));
    }

    @Override
    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
