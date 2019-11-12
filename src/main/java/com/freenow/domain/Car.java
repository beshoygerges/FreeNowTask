package com.freenow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freenow.domainvalue.EngineType;
import com.freenow.dto.CarDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
public class Car implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private Double rating = 0d;

    @Column(nullable = false)
    private Integer seatCount = 1;

    @Column(nullable = false)
    private String manufacturer;

    @Enumerated(value = EnumType.STRING)
    private EngineType engineType;

    @JsonIgnore
    @Column(nullable = false)
    private Boolean deleted = false;

    @JsonIgnore
    @JoinColumn(name = "DriverId")
    @OneToOne
    private Driver driver;

    public Car() {
    }

    public Car(CarDTO carDto) {
        this.setRating(carDto.getRating());
        this.setEngineType(carDto.getEngineType());
        this.setLicensePlate(carDto.getLicensePlate());
        this.setSeatCount(carDto.getSeatCount());
        this.setManufacturer(carDto.getManufacturer());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(ZonedDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public boolean isReserved() {
        return getDriver() != null;
    }

    public boolean isMyDriver(Driver driver) {
        return this.driver.equals(driver);
    }
}
