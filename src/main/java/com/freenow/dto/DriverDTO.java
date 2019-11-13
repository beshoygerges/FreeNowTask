package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
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
    @NotNull(message = "Username can not be null!")
    private String username;
    @NotNull(message = "Password can not be null!")
    private String password;
    private Car car;
    private GeoCoordinate coordinate;

    public DriverDTO(Long id, String username, String password, GeoCoordinate coordinate, Car car) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
        this.car = car;
    }

    public static DriverDTOBuilder newBuilder() {
        return new DriverDTOBuilder();
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
