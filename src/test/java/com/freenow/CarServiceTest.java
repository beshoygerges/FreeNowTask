package com.freenow;

import com.freenow.domain.Car;
import com.freenow.dto.CarDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.repository.CarRepository;
import com.freenow.service.CarService;
import com.freenow.service.ICarService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CarServiceTest {

    private ICarService carService = null;
    private CarRepository carRepository;

    @Before
    public void setUp() {
        carRepository = mock(CarRepository.class);
        carService = new CarService(carRepository);
    }

    @Test(expected = DriversManagementException.EntityNotFoundException.class)
    public void FetchNotExistCarThrows() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        carService.fetchById(1L);
    }

    @Test
    public void FetchExistCarReturnsResponse() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        CarDTO dto = carService.fetchById(1L);
        Assert.assertNotEquals(null, dto);
    }

    @Test
    public void DeleteExistCarPass() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        carService.deleteCar(1L);
    }

    @Test(expected = DriversManagementException.EntityNotFoundException.class)
    public void DeleteNonExistCarThrows() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        carService.deleteCar(1L);
    }

    @Test
    public void SaveNewCarPass() throws DriversManagementException.ConstraintsViolationException {
        when(carRepository.save(any())).thenReturn(new Car());
        Car car = new Car();
        car.setLicensePlate("125478");
        CarDTO dto = carService.addCar(new CarDTO(car));
        Assert.assertNotEquals(null, dto);
    }

    @Test(expected = DriversManagementException.ConstraintsViolationException.class)
    public void SaveExistCarThrows() throws DriversManagementException.ConstraintsViolationException {
        when(carRepository.save(any())).thenThrow(new DataIntegrityViolationException("primary key violation"));
        Car car = new Car();
        car.setLicensePlate("125478");
        carService.addCar(new CarDTO(car));
    }

    @Test(expected = DriversManagementException.EntityNotFoundException.class)
    public void UpdateNonExistCarThrows() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.empty());
        carService.updateCar(2.1, 1L);
    }

    @Test
    public void UpdateExistCarPass() throws DriversManagementException.EntityNotFoundException {
        when(carRepository.findById(1L)).thenReturn(Optional.of(new Car()));
        carService.updateCar(2.1, 1L);
    }
}
