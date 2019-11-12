package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domain.Driver;
import com.freenow.domainvalue.GeoCoordinate;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
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
