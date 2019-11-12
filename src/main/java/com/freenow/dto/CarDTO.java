package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domainvalue.EngineType;
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


}
