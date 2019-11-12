package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domainvalue.EngineType;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @NotNull
    private String licensePlate;
    @NotNull
    private Integer seatCount;
    private Double rating;
    @NotNull
    private EngineType engineType;
    @NotNull
    private String manufacturer;

    public CarDTO() {
    }

    public CarDTO(Car car) {
        this.setEngineType(car.getEngineType());
        this.setLicensePlate(car.getLicensePlate());
        this.setManufacturer(car.getManufacturer());
        this.setRating(car.getRating());
        this.setSeatCount(car.getSeatCount());
        this.setId(car.getId());
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Integer getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(Integer seatCount) {
        this.seatCount = seatCount;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public void setEngineType(EngineType engineType) {
        this.engineType = engineType;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }
}
