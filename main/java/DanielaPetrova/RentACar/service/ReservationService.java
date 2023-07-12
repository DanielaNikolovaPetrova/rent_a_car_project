package DanielaPetrova.RentACar.service;

import DanielaPetrova.RentACar.dto.ReservationRequest;
import DanielaPetrova.RentACar.dto.ReservationResponse;
import DanielaPetrova.RentACar.entity.Reservation;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public interface ReservationService {
    Reservation bookReservation(ReservationRequest response);
    ReservationResponse getById(Long id);
    Set<Reservation> getByUser(Long userId);
    Set<Reservation> getByCar(Long carId);
    Set<Reservation> getByPeriod(String startDate, String endDate);
    ReservationResponse updateReservation(Long id, ReservationRequest request);
    void deleteReservation(Long id);
}
