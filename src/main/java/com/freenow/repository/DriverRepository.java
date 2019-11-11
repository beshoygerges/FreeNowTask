package com.freenow.repository;

import com.freenow.domain.Driver;
import com.freenow.domainvalue.OnlineStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<Driver, Long> {

    List<Driver> findByOnlineStatus(OnlineStatus onlineStatus);
}
