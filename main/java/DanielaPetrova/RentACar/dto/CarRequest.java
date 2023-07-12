package DanielaPetrova.RentACar.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CarRequest {
    private String brand;
    private String model;
    private Integer numberOfSeats;
    private Double rentForDay;
}
