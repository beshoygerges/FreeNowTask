package com.freenow.service;

import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;
import com.freenow.exception.DriversManagementException;

import java.util.List;

public interface IDriverService {

    DriverDTO findByStatus(Long driverId) throws DriversManagementException.EntityNotFoundException, DriversManagementException.EntityNotFoundException;

    DriverDTO create(DriverDTO driverDO) throws DriversManagementException.ConstraintsViolationException, DriversManagementException.ConstraintsViolationException;

    void delete(Long driverId) throws DriversManagementException.EntityNotFoundException, DriversManagementException.EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws DriversManagementException.EntityNotFoundException, DriversManagementException.EntityNotFoundException;

    List<DriverDTO> findByStatus(OnlineStatus onlineStatus);

}
