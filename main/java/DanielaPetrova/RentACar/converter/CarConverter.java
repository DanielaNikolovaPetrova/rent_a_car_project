package DanielaPetrova.RentACar.converter;

import DanielaPetrova.RentACar.dto.CarRequest;
import DanielaPetrova.RentACar.dto.CarResponse;
import DanielaPetrova.RentACar.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {
    public Car toCar(CarRequest request){
        return Car.builder()
                .brand(request.getBrand())
                .model(request.getModel())
                .numberOfSeats(request.getNumberOfSeats())
                .rentPerDay(request.getRentForDay())
                .build();
    }
    public CarResponse toCarResponse(Car savedCar){
        return new CarResponse(savedCar.getBrand(), savedCar.getModel(),
                savedCar.getNumberOfSeats(), savedCar.getRentPerDay());
    }
}
