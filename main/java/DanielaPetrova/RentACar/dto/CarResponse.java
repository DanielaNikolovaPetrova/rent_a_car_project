package DanielaPetrova.RentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CarResponse {
    private String brand;
    private String model;
    private Integer numberOfSeats;
    private Double rentPerDay;
}
