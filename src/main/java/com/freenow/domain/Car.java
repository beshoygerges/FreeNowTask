package com.freenow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.freenow.domainvalue.EngineType;
import com.freenow.dto.CarDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
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

    public Car(CarDTO carDto) {
        this.setRating(carDto.getRating());
        this.setEngineType(carDto.getEngineType());
        this.setLicensePlate(carDto.getLicensePlate());
        this.setSeatCount(carDto.getSeatCount());
        this.setManufacturer(carDto.getManufacturer());
    }

    public boolean isReserved() {
        return getDriver() != null;
    }

    public boolean isMyDriver(Driver driver) {
        return this.driver.equals(driver);
    }
}
