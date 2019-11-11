package com.freenow.service;

import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;

import java.util.List;

import static com.freenow.exception.DriversManagementException.ConstraintsViolationException;
import static com.freenow.exception.DriversManagementException.EntityNotFoundException;

public interface IDriverService {

    DriverDTO findById(Long driverId) throws EntityNotFoundException;

    DriverDTO create(DriverDTO driverDTO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDTO> findByStatus(OnlineStatus onlineStatus);

}
