package com.freenow;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.freenow.dto.CarDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = FreeNowServerApplicantTestApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FreeNowServerApplicantTestApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void FetchAllCarsWithoutAuthReturnsUnauthorized() {
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:" + port + "/v1/cars", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED.value(), response.getStatusCode().value());
    }

    @Test
    public void FetchAllCarsWithAuthReturnsList() throws IOException {
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "P@ssw0rd").getForEntity("http://localhost:" + port + "/v1/cars", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        assertEquals(6, unMarshall(response.getBody(), List.class).size());
    }

    @Test
    public void FetchCarByIdWithAuthReturnsCarDetails() throws IOException {
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "P@ssw0rd").getForEntity("http://localhost:" + port + "/v1/cars/1", String.class);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        CarDTO car = unMarshall(response.getBody(), CarDTO.class);
        assertNotEquals(null, car);
    }

    @Test
    public void AcquireUnReservedCarShouldReturnsOk() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "P@ssw0rd").getForEntity("http://localhost:" + port + "v1/drivers/acquire?driverId=3&carId=2", null);
        assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
    }

    @Test
    public void AcquireReservedCarShouldReturnsBadRequest() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("admin", "P@ssw0rd").getForEntity("http://localhost:" + port + "v1/drivers/acquire?driverId=5&carId=5", null);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
    }

    private <T> T unMarshall(String json, Class<T> c) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, c);
    }


}
