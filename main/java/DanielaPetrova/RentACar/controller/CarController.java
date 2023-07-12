package DanielaPetrova.RentACar.controller;

import DanielaPetrova.RentACar.dto.CarRequest;
import DanielaPetrova.RentACar.dto.CarResponse;
import DanielaPetrova.RentACar.service.CarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/car")
public class CarController {
    @Autowired
    CarService carService;
    @PostMapping("/saveCar")
    public ResponseEntity<CarResponse> saveCar(@Valid @RequestBody CarRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(carService.saveCar(request));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(carService.getById(id));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<CarResponse> updateCar(@PathVariable Long id, @RequestBody CarRequest request){
        CarResponse carResponse = carService.updateCar(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(carResponse);
    }

    @DeleteMapping(path = "/{id}")
    public HttpStatus deleteCar(Long id){
        carService.deleteCar(id);
        return HttpStatus.ACCEPTED;
    }
}
