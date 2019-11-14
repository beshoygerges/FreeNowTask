package com.freenow.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.freenow.domain.Car;
import com.freenow.domainvalue.EngineType;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;


@NoArgsConstructor
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
public class CarDTO implements Serializable {

    @JsonProperty
    private Long id;
    @JsonProperty
    @NotNull
    private String licensePlate;
    @JsonProperty
    @NotNull
    private Integer seatCount;
    @JsonProperty
    private Double rating;
    @JsonProperty
    @NotNull
    private EngineType engineType;
    @JsonProperty
    @NotNull
    private String manufacturer;

    public CarDTO(Long id, Double rating, String manufacturer, EngineType engineType, Integer seatCount, String licensePlate) {
        this.id = id;
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.engineType = engineType;
        this.seatCount = seatCount;
        this.licensePlate = licensePlate;
    }


    public static CarDTOBuilder newBuilder() {
        return new CarDTOBuilder();
    }

    public Car toCar() {
        return Car.newBuilder()
                .setEngineType(engineType)
                .setLicensePlate(licensePlate)
                .setManufacturer(manufacturer)
                .setRating(rating)
                .setSeatCount(seatCount)
                .build();
    }

    public static class CarDTOBuilder {

        private Long id;
        private String licensePlate;
        private Integer seatCount;
        private Double rating;
        private EngineType engineType;
        private String manufacturer;

        public CarDTOBuilder setId(Long id) {
            this.id = id;
            return this;
        }

        public CarDTOBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarDTOBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarDTOBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public CarDTOBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarDTOBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarDTO build() {
            return new CarDTO(id, rating, manufacturer, engineType, seatCount, licensePlate);
        }
    }


}
