package DanielaPetrova.RentACar.controller;

import DanielaPetrova.RentACar.converter.ReservationConverter;
import DanielaPetrova.RentACar.dto.ReservationRequest;
import DanielaPetrova.RentACar.dto.ReservationResponse;
import DanielaPetrova.RentACar.entity.Reservation;
import DanielaPetrova.RentACar.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(path = "api/v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationConverter reservationConverter;
    @PostMapping("/book")
    public ResponseEntity<Reservation> bookReservation (@Valid @RequestBody ReservationRequest reservationRequest) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.bookReservation(reservationRequest));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationService.getById(id));
    }
    @GetMapping(path = "/reservationByUser/{userId}")
    public ResponseEntity<Set<ReservationResponse>> getReservationByUser(@PathVariable Long userId){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.getByUser(userId).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
    @GetMapping(path = "/reservationByCar/{carId}")
    public ResponseEntity<Set<ReservationResponse>> getReservationByCar(@PathVariable Long carId){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.getByCar(carId).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }

    @GetMapping(path = "/period")
    public ResponseEntity<Set<ReservationResponse>> getReservationByPeriod(@RequestParam("startDate") String startDate,
                                                                           @RequestParam("endDate")String endDate){
        Set<ReservationResponse> reservationResponses = new HashSet<>();
        reservationService.getByPeriod(startDate, endDate).forEach(
                reservation -> {
                    ReservationResponse reservationResponse = reservationConverter.toReservationResponse(reservation);
                    reservationResponses.add(reservationResponse);
                }
        );

        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponse> updateReservation(@PathVariable Long id,
                                                   @RequestBody ReservationRequest reservationRequest){
        ReservationResponse reservationResponse = reservationService.updateReservation(id, reservationRequest);
        return ResponseEntity.status(HttpStatus.FOUND).body(reservationResponse);
    }
    @DeleteMapping("/{id}")
    public HttpStatus deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return HttpStatus.ACCEPTED;
    }
}
