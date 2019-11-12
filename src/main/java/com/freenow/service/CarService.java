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
        return new CarDTO(findCarChecked(id));
    }

    @Override
    public CarDTO addCar(CarDTO carDto) throws DriversManagementException.ConstraintsViolationException {
        Car car = new Car(carDto);
        try {
            car = carRepository.save(car);
        } catch (DataIntegrityViolationException e) {
            throw new DriversManagementException.ConstraintsViolationException(e.getMessage());
        }
        return new CarDTO(car);
    }

    @Transactional
    @Override
    public void updateCar(Double rating, Long id) throws DriversManagementException.EntityNotFoundException {
        Car car = findCarChecked(id);
        car.setRating(rating);
    }

    @Transactional
    @Override
    public void deleteCar(Long id) throws DriversManagementException.EntityNotFoundException {
        Car car = findCarChecked(id);
        car.setDeleted(true);
    }

    @Override
    public List<CarDTO> fetchAllCars() {
        return StreamSupport.stream(carRepository.findAll().spliterator(), false)
                .map(CarDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Car findCarChecked(Long id) throws DriversManagementException.EntityNotFoundException {
        return carRepository.findById(id).orElseThrow(() -> new DriversManagementException.EntityNotFoundException(id));
    }
}
