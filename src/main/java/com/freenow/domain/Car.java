package com.freenow.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freenow.domainvalue.EngineType;
import com.freenow.dto.CarDTO;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;

@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE)
@NoArgsConstructor
@EqualsAndHashCode
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


    public Car(Double rating, String manufacturer, Integer seatCount, EngineType engineType, String licensePlate) {
        this.rating = rating;
        this.manufacturer = manufacturer;
        this.seatCount = seatCount;
        this.engineType = engineType;
        this.licensePlate = licensePlate;
    }

    public static CarBuilder newBuilder() {
        return new CarBuilder();
    }

    public CarDTO toCarDTO() {
        return CarDTO.newBuilder()
                .setEngineType(engineType)
                .setId(id)
                .setLicensePlate(licensePlate)
                .setManufacturer(manufacturer)
                .setRating(rating)
                .setSeatCount(seatCount)
                .build();
    }

    public boolean isMyDriver(Driver driver) {
        return this.driver.equals(driver);
    }

    public boolean isReserved() {
        return this.driver != null;
    }

    public void remove() {
        this.deleted = true;
    }

    public void increaseRating(Double rating) {
        this.rating += rating;
    }

    public void removeDriver() {
        this.driver = null;
    }

    public void acquireBy(Driver driver) {
        this.driver = driver;
    }

    public static class CarBuilder {

        private EngineType engineType;
        private String manufacturer;
        private String licensePlate;
        private Integer seatCount = 0;
        private Double rating = 0d;

        public CarBuilder setEngineType(EngineType engineType) {
            this.engineType = engineType;
            return this;
        }

        public CarBuilder setManufacturer(String manufacturer) {
            this.manufacturer = manufacturer;
            return this;
        }

        public CarBuilder setLicensePlate(String licensePlate) {
            this.licensePlate = licensePlate;
            return this;
        }

        public CarBuilder setSeatCount(Integer seatCount) {
            this.seatCount = seatCount;
            return this;
        }

        public CarBuilder setRating(Double rating) {
            this.rating = rating;
            return this;
        }

        public Car build() {
            return new Car(rating, manufacturer, seatCount, engineType, licensePlate);
        }
    }
}
