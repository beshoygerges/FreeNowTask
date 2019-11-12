package com.freenow;

import com.freenow.service.IDriverSearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DriverSearchServiceTest {

    @Autowired
    private IDriverSearchService driverSearchService;

    @Test
    public void FetchByUsernameReturnsNonEmptyList() {
        Assert.assertNotEquals(0, driverSearchService.fetchByUserName("driver01").size());
    }

    @Test
    public void FetchNonExistDriverByUsernameReturnsEmptyList() {
        Assert.assertEquals(0, driverSearchService.fetchByUserName("driver12").size());
    }

    @Test
    public void FetchByRatingReturnsEmptyList() {
        Assert.assertEquals(0, driverSearchService.fetchByRating(1.0).size());
    }

    @Test
    public void FetchByRatingReturnsOneElementList() {
        Assert.assertEquals(1, driverSearchService.fetchByRating(9.1).size());
    }

    @Test
    public void FetchByLicensePlateReturnsTwoElementsList() {
        Assert.assertEquals(2, driverSearchService.fetchByLicensePlate("5478512").size());
    }

}
