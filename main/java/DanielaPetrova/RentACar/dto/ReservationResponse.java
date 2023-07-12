package DanielaPetrova.RentACar.dto;

import DanielaPetrova.RentACar.entity.Car;
import DanielaPetrova.RentACar.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ReservationResponse {
    private Long id;
    private int rentalDays;
    private String startDate;
    private String endDate;
    private Car car;
    private User user;
}
