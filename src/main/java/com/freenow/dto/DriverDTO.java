package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domain.Driver;
import com.freenow.domainvalue.GeoCoordinate;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;


@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DriverDTO implements Serializable {

    @JsonIgnore
    private Long id;
    @JsonProperty
    @NotNull(message = "Username can not be null!")
    private String username;
    @JsonProperty
    @NotNull(message = "Password can not be null!")
    private String password;
    @JsonIgnore
    private Car car;

    private GeoCoordinate coordinate;

    public DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, Car car) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.car = car;
    }

    @JsonProperty
    public Long getId() {
        return id;
    }

    @JsonProperty
    public Car getCar() {
        return car;
    }

    public GeoCoordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    public static DriverDTOBuilder newBuilder() {
        return new DriverDTOBuilder();
    }

    public Driver toDriver() {
        return Driver.newBuilder()
                .setCoordinate(coordinate)
                .setPassword(password)
                .setUsername(username)
                .build();
    }

    public static class DriverDTOBuilder {
        private Long id;
        private String username;
        private String password;
        private GeoCoordinate coordinate;
        private Car car;

        public DriverDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public DriverDTOBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DriverDTOBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverDTOBuilder setCoordinate(GeoCoordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public DriverDTOBuilder setCar(Car car) {
            this.car = car;
            return this;
        }

        public DriverDTO build() {
            return new DriverDTO(id, username, password, coordinate, car);
        }

    }
}
