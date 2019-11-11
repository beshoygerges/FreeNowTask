package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.DriverDomain;
import com.freenow.domainvalue.GeoCoordinate;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO {
    @JsonIgnore
    private Long id;

    @NotNull(message = "Username can not be null!")
    private String username;

    @NotNull(message = "Password can not be null!")
    private String password;

    private GeoCoordinate coordinate;


    private DriverDTO() {
    }


    private DriverDTO(Long id, String username, String password, GeoCoordinate coordinate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
    }

    public DriverDTO(DriverDomain driver) {
        this.id = driver.getId();
        if (driver.getCoordinate() != null)
            this.coordinate = driver.getCoordinate();
        this.password = driver.getPassword();
        this.username = driver.getUsername();
    }


    @JsonProperty
    public Long getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }


    public String getPassword() {
        return password;
    }


    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

}
