package com.freenow.service;

import com.freenow.controller.mapper.DriverMapper;
import com.freenow.domain.DriverDomain;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.repository.DriverRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.freenow.exception.DriversManagementException.ConstraintsViolationException;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverService implements IDriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverService.class);

    @Autowired
    private DriverRepository driverRepository;


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws DriversManagementException.EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    public DriverDTO findByStatus(Long driverId) throws DriversManagementException.EntityNotFoundException {
        return new DriverDTO(findDriverChecked(driverId));
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

        DriverDomain driver = new DriverDomain(driverDTO);
        try {
            driver = driverRepository.save(driver);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driver, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return new DriverDTO(driver);
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
        DriverDomain driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
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
        DriverDomain driverDO = findDriverChecked(driverId);
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
        return DriverMapper.makeDriverDTOList(driverRepository.findByOnlineStatus(onlineStatus));
    }


    private DriverDomain findDriverChecked(Long driverId) throws DriversManagementException.EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new DriversManagementException.EntityNotFoundException("Could not find entity with id: " + driverId));
    }

}
