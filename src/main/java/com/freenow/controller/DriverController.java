package com.freenow.controller;

import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.service.IDriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static com.freenow.exception.DriversManagementException.ConstraintsViolationException;
import static com.freenow.exception.DriversManagementException.EntityNotFoundException;

/**
 * All operations with a driver will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private final IDriverService driverService;

    @Autowired
    public DriverController(IDriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException {
        return driverService.findById(driverId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        return driverService.create(driverDTO);
    }

    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException {
        driverService.delete(driverId);
    }

    @PutMapping("/{driverId}")
    public void updateLocation(
            @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException {
        driverService.updateLocation(driverId, longitude, latitude);
    }

    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus) {
        return driverService.findByStatus(onlineStatus);
    }

    @GetMapping("/acquire")
    public void acquireCar(@RequestParam(name = "driverId") Long driverId, @RequestParam(name = "carId") Long carId) throws DriversManagementException.CarAlreadyInUseException, EntityNotFoundException, DriversManagementException.CarAcquirerLimitationException {
        driverService.acquireCar(driverId, carId);
    }

    @GetMapping("/release")
    public void releaseCar(@RequestParam(name = "driverId") Long driverId, @RequestParam(name = "carId") Long carId) throws DriversManagementException.CarAlreadyInUseException, EntityNotFoundException, DriversManagementException.IllegalCarAccessException, DriversManagementException.CarNotAcquiredException {
        driverService.releaseCar(driverId, carId);
    }
}
