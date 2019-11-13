package com.freenow.service;

import com.freenow.domain.Car;
import com.freenow.domain.Driver;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.freenow.exception.DriversManagementException.ConstraintsViolationException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements IDriverService {

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private ICarService carService;

    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws DriversManagementException.EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDTO findById(Long driverId) throws DriversManagementException.EntityNotFoundException {
        return findDriverChecked(driverId).toDriverDTO();
    }

    /**
     * Creates a new driver.
     *
     * @param driverDTO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    public DriverDTO create(DriverDTO driverDTO) throws ConstraintsViolationException {

        Driver driver = driverDTO.toDriver();
        try {
            driver = driverRepository.save(driver);
        } catch (DataIntegrityViolationException e) {
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver.toDriverDTO();
    }

    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws DriversManagementException.EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    public void delete(Long driverId) throws DriversManagementException.EntityNotFoundException {
        Driver driverDO = findDriverChecked(driverId);
        driverDO.remove();
    }

    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws DriversManagementException.EntityNotFoundException
     */
    @Override
    @Transactional
    public void updateLocation(long driverId, double longitude, double latitude) throws DriversManagementException.EntityNotFoundException {
        Driver driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
    }

    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     * @return
     */
    @Override
    public List<DriverDTO> findByStatus(OnlineStatus onlineStatus) {
        return StreamSupport.stream(driverRepository.findByOnlineStatus(onlineStatus).spliterator(), false)
                .map(driver -> driver.toDriverDTO())
                .collect(Collectors.toList());

    }

    @Transactional
    @Override
    public void releaseCar(Long driverId, Long carId) throws DriversManagementException.EntityNotFoundException, DriversManagementException.CarAlreadyInUseException, DriversManagementException.IllegalCarAccessException, DriversManagementException.CarNotAcquiredException {
        Driver driver = findDriverChecked(driverId);
        Car car = carService.findCarChecked(carId);
        if (!car.isReserved()) throw new DriversManagementException.CarNotAcquiredException();
        if (!car.isMyDriver(driver)) throw new DriversManagementException.IllegalCarAccessException();
        car.removeDriver();
    }

    @Transactional
    @Override
    public void acquireCar(Long driverId, Long carId) throws DriversManagementException.EntityNotFoundException, DriversManagementException.CarAlreadyInUseException, DriversManagementException.CarAcquirerLimitationException {
        Driver driver = findDriverChecked(driverId);
        Car car = carService.findCarChecked(carId);
        if (driver.hasCar()) throw new DriversManagementException.CarAcquirerLimitationException();
        if (car.isReserved()) throw new DriversManagementException.CarAlreadyInUseException();
        car.acquireBy(driver);
    }

    private Driver findDriverChecked(Long driverId) throws DriversManagementException.EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new DriversManagementException.EntityNotFoundException(driverId));
    }

}
