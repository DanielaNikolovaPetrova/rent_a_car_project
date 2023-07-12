package DanielaPetrova.RentACar.service;

import DanielaPetrova.RentACar.dto.CarRequest;
import DanielaPetrova.RentACar.dto.CarResponse;
import org.springframework.stereotype.Service;

@Service
public interface CarService {
    CarResponse saveCar(CarRequest request);
    CarResponse getById(Long id);
    CarResponse updateCar(Long id, CarRequest request);
    void deleteCar(Long id);
}
