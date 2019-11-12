package com.freenow.service;

import com.freenow.domain.Driver;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverSearchService implements IDriverSearchService {

    private final DriverRepository driverRepository;

    @Autowired
    public DriverSearchService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> fetchByUserName(String username) {
        return driverRepository.findByUsername(username);
    }

    @Override
    public List<Driver> fetchByOnlineStatus(OnlineStatus status) {
        return driverRepository.findByOnlineStatus(status);
    }

    @Override
    public List<Driver> fetchByLicensePlate(String licensePlate) {
        return driverRepository.findByCar_LicensePlate(licensePlate);
    }

    @Override
    public List<Driver> fetchByRating(Double rating) {
        return driverRepository.findByCar_Rating(rating);
    }
}
