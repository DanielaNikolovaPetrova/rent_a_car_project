package DanielaPetrova.RentACar.service.impl;

import DanielaPetrova.RentACar.converter.CarConverter;
import DanielaPetrova.RentACar.dto.CarRequest;
import DanielaPetrova.RentACar.dto.CarResponse;
import DanielaPetrova.RentACar.entity.Car;
import DanielaPetrova.RentACar.exception.NotFoundException;
import DanielaPetrova.RentACar.repository.CarRepository;
import DanielaPetrova.RentACar.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarConverter carConverter;

    public CarServiceImpl(CarRepository carRepository, CarConverter carConverter) {
        this.carRepository = carRepository;
        this.carConverter = carConverter;
    }

    @Override
    public CarResponse saveCar(CarRequest request) {
        Car car = carConverter.toCar(request);
        Car savedCar = carRepository.save(car);
        return carConverter.toCarResponse(savedCar);
    }

    @Override
    public CarResponse getById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Car not found")
        );
        return carConverter.toCarResponse(car);
    }

    @Override
    public CarResponse updateCar(Long id, CarRequest request) {
        Car car = carRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Car not found")
        );;
        if(request.getBrand() != null && !request.getBrand().isBlank()){
            car.setBrand(request.getBrand());
        }
        if(request.getModel() != null && !request.getModel().isBlank()){
            car.setModel(request.getModel());
        }
        if(request.getNumberOfSeats() != null){
            car.setNumberOfSeats(request.getNumberOfSeats());
        }
        if (request.getRentForDay() != null){
            car.setRentPerDay(request.getRentForDay());
        }
        return carConverter.toCarResponse(carRepository.save(car));
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}
