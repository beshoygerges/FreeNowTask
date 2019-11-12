package com.freenow.controller;

import com.freenow.domain.Driver;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.service.IDriverSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/drivers")
public class DriverSearchController {

    private final IDriverSearchService driverSearchService;

    @Autowired
    public DriverSearchController(IDriverSearchService driverSearchService) {
        this.driverSearchService = driverSearchService;
    }

    @GetMapping("/username")
    public List<Driver> findByUsername(@RequestParam String username) {
        return driverSearchService.fetchByUserName(username);
    }

    @GetMapping("/status")
    public List<Driver> findByStatus(@RequestParam OnlineStatus status) {
        return driverSearchService.fetchByOnlineStatus(status);
    }

    @GetMapping("/licensePlate")
    public List<Driver> findByStatus(@RequestParam String licensePlate) {
        return driverSearchService.fetchByLicensePlate(licensePlate);
    }

    @GetMapping("/rating")
    public List<Driver> findByRating(@RequestParam Double rating) {
        return driverSearchService.fetchByRating(rating);
    }


}
