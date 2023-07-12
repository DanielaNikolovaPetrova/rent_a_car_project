package DanielaPetrova.RentACar.converter;

import DanielaPetrova.RentACar.dto.ReservationRequest;
import DanielaPetrova.RentACar.dto.ReservationResponse;
import DanielaPetrova.RentACar.entity.Car;
import DanielaPetrova.RentACar.entity.Reservation;
import DanielaPetrova.RentACar.entity.User;
import DanielaPetrova.RentACar.exception.NotFoundException;
import DanielaPetrova.RentACar.repository.CarRepository;
import DanielaPetrova.RentACar.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Component
public class ReservationConverter {
    @Autowired
    CarRepository carRepository;
    @Autowired
    UserRepository userRepository;
    public Reservation toReservation(ReservationRequest request) {
        Car car = carRepository.findById(request.getCarId()).orElseThrow(
                ()-> new NotFoundException("Car not found")
        );
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        int days = estimateRentalDays(request.getStartDate(), request.getEndDate());
        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .rentalDays(days)
                .price(days * car.getRentPerDay())
                .build();

        return reservation;
    }
    public ReservationResponse toReservationResponse(Reservation reservation){
        Car car = carRepository.findById(reservation.getCar().getId()).orElseThrow(
                () -> new NotFoundException("Car not found")
        );
        User user = userRepository.findById(reservation.getUser().getId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );
        return ReservationResponse.builder()
                .rentalDays(reservation.getRentalDays())
                .startDate((reservation.getStartDate().toString()))
                .endDate(reservation.getEndDate().toString())
                .car(car)
                .user(user)
                .build();

    }

    int estimateRentalDays(Instant startDate, Instant endDate){
        Duration duration = Duration.between(startDate, endDate);
        Long result = duration.toDaysPart();
        return Integer.parseInt(result.toString());
    }
//    int estimateRentalDaysDate(Date startDate, Date endDate) {
//        Instant start = Instant.parse(startDate.toString());
//        Instant end = Instant.parse(endDate.toString());
//        Duration duration = Duration.between(start, end);
//        Long result = duration.toDaysPart();
//        return Integer.parseInt(result.toString());
//    }
//    int calculateDays(Date startDate, Date endDate) {
//        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MM yyyy");
//        String inputString1 = startDate.toString();
//        String inputString2 = endDate.toString();
//
//            LocalDate date1 = LocalDate.parse(inputString1, dtf);
//            LocalDate date2 = LocalDate.parse(inputString2, dtf);
//            Duration duration = Duration.between(date1, date2);
//            Long daysBetween = duration.toDays();
//            int result = Integer.parseInt(daysBetween.toString());
//       return result;
//    }
}
