package com.freenow.service;

import com.freenow.domain.Car;
import com.freenow.dto.CarDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarService implements ICarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDTO fetchById(Long id) throws DriversManagementException.EntityNotFoundException {
        return findCarChecked(id).toCarDTO();
    }

    @Override
    public CarDTO addCar(CarDTO carDto) throws DriversManagementException.ConstraintsViolationException {
        Car car = carDto.toCar();
        try {
            car = carRepository.save(car);
        } catch (DataIntegrityViolationException e) {
            throw new DriversManagementException.ConstraintsViolationException(e.getMessage());
        }
        return car.toCarDTO();
    }

    @Transactional
    @Override
    public void updateCar(Double rating, Long id) throws DriversManagementException.EntityNotFoundException {
        Car car = findCarChecked(id);
        car.increaseRating(rating);
    }

    @Transactional
    @Override
    public void deleteCar(Long id) throws DriversManagementException.EntityNotFoundException {
        Car car = findCarChecked(id);
        car.remove();
    }

    @Override
    public List<CarDTO> fetchAllCars() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .map(Car::toCarDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Car findCarChecked(Long id) throws DriversManagementException.EntityNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new DriversManagementException.EntityNotFoundException(id));
    }
}
