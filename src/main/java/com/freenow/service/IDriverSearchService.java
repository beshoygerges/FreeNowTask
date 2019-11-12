package com.freenow.service;

import com.freenow.domain.Driver;
import com.freenow.domainvalue.OnlineStatus;

import java.util.List;

public interface IDriverSearchService {

    List<Driver> fetchByUserName(String username);

    List<Driver> fetchByOnlineStatus(OnlineStatus status);

    List<Driver> fetchByLicensePlate(String licensePlate);

    List<Driver> fetchByRating(Double rating);

}
