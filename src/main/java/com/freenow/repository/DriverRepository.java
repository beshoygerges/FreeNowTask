package com.freenow.repository;

import com.freenow.domain.Driver;
import com.freenow.domainvalue.OnlineStatus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
@Repository
public interface DriverRepository extends CrudRepository<Driver, Long> {

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);

    List<Driver> findByUsername(String username);

    List<Driver> findByCarLicensePlate(String licensePlate);

    List<Driver> findByCarRating(Double rating);

}
