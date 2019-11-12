package com.freenow.service;

import com.freenow.domain.Car;
import com.freenow.dto.CarDTO;
import com.freenow.exception.DriversManagementException;

import java.util.List;

public interface ICarService {

    CarDTO fetchById(Long id) throws DriversManagementException.EntityNotFoundException;

    CarDTO addCar(CarDTO car) throws DriversManagementException.ConstraintsViolationException;

    void updateCar(Double rating, Long id) throws DriversManagementException.EntityNotFoundException;

    void deleteCar(Long id) throws DriversManagementException.EntityNotFoundException;

    List<CarDTO> fetchAllCars();


    Car findCarChecked(Long id) throws DriversManagementException.EntityNotFoundException;
}
