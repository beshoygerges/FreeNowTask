package com.freenow.repository;

import com.freenow.domain.DriverDomain;
import com.freenow.domainvalue.OnlineStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDomain, Long> {

    List<DriverDomain> findByOnlineStatus(OnlineStatus onlineStatus);
}
