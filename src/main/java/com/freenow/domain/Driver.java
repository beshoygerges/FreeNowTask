package com.freenow.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.freenow.domainvalue.GeoCoordinate;
import com.freenow.domainvalue.OnlineStatus;
import com.freenow.dto.DriverDTO;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Table(
        name = "driver",
        uniqueConstraints = @UniqueConstraint(name = "uc_username", columnNames = {"username"})
)
@EqualsAndHashCode
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Driver implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCreated = ZonedDateTime.now();
    @Column(nullable = false)
    @NotNull(message = "Username can not be null!")
    private String username;
    @Column(nullable = false)
    @NotNull(message = "Password can not be null!")
    private String password;
    @JsonIgnore
    @Column(nullable = false)
    private Boolean deleted = false;
    @Embedded
    private GeoCoordinate coordinate;
    @Column
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime dateCoordinateUpdated = ZonedDateTime.now();
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private OnlineStatus onlineStatus = OnlineStatus.OFFLINE;
    @OneToOne(mappedBy = "driver", fetch = FetchType.LAZY)
    private Car car;

    public Driver(String username, String password, GeoCoordinate coordinate) {
        this.username = username;
        this.password = password;
        this.coordinate = coordinate;
    }

    public static DriverBuilder newBuilder() {
        return new DriverBuilder();
    }

    public void setCoordinate(GeoCoordinate coordinate) {
        this.coordinate = coordinate;
        this.dateCoordinateUpdated = ZonedDateTime.now();
    }

    public boolean hasCar() {
        return this.car != null;
    }

    public DriverDTO toDriverDTO() {
        return DriverDTO.newBuilder()
                .setCar(car)
                .setCoordinate(coordinate)
                .setPassword(password)
                .setId(id)
                .setUsername(username)
                .build();
    }

    public void remove() {
        this.deleted = true;
    }

    public static class DriverBuilder {

        private String username;
        private String password;
        private GeoCoordinate coordinate;

        public DriverBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DriverBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DriverBuilder setCoordinate(GeoCoordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Driver build() {
            return new Driver(username, password, coordinate);
        }
    }

}
