package com.freenow.controller;

import com.freenow.dto.CarDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.service.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/cars")
public class CarController {

    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<CarDTO> fetchAllCars() {
        return carService.fetchAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO fetchCarById(@PathVariable Long id) throws DriversManagementException.EntityNotFoundException {
        return carService.fetchById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws DriversManagementException.EntityNotFoundException {
        carService.deleteCar(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestParam Double rating) throws DriversManagementException.EntityNotFoundException {
        carService.updateCar(rating, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO addCar(@RequestBody CarDTO carDTO) throws DriversManagementException.ConstraintsViolationException {
        return carService.addCar(carDTO);
    }


}
