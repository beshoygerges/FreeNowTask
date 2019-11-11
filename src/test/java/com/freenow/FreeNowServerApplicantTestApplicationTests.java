package com.freenow;

import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.dto.DriverDTO;
import com.freenow.exception.DriversManagementException;
import com.freenow.service.IDriverService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FreeNowServerApplicantTestApplicationTests {

    @Autowired
    private IDriverService driverService;

    @Test(expected = DriversManagementException.EntityNotFoundException.class)
    public void NotFoundDriverThrowsException() throws DriversManagementException.EntityNotFoundException {
        driverService.findById(100l);
    }

    @Test
    public void FindDriverReturnsOne() throws DriversManagementException.EntityNotFoundException {
        DriverDTO driver = driverService.findById(1l);
        Assert.assertNotEquals(driver, null);
    }

    @Test
    public void CreateDriverReturnsTheCreatedOne() throws DriversManagementException.ConstraintsViolationException {
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setPassword("123456");
        driverDTO.setUsername("abcdef");
        driverDTO.setCoordinate(new GeoCoordinate(90, 90));
        DriverDTO response = driverService.create(driverDTO);
        Assert.assertNotEquals(response, null);
    }

}
