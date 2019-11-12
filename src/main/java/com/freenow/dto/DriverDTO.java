package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domain.Driver;
import com.freenow.domainvalue.GeoCoordinate;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO implements Serializable {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private Car car;

    private GeoCoordinate coordinate;

    public DriverDTO() {
    }

    public DriverDTO(Driver driver) {
        this.id = driver.getId();
        this.coordinate = driver.getCoordinate();
        this.password = driver.getPassword();
        this.username = driver.getUsername();
        this.car = driver.getCar();
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DriverDTO driverDTO = (DriverDTO) o;
        return getId().equals(driverDTO.getId()) &&
                getUsername().equals(driverDTO.getUsername()) &&
                getPassword().equals(driverDTO.getPassword()) &&
                getCoordinate().equals(driverDTO.getCoordinate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getCoordinate());
    }

    @Override
    public String toString() {
        return "DriverDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", coordinate=" + coordinate +
                '}';
    }
}
