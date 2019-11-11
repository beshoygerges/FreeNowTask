package com.freenow.controller.mapper;

import com.freenow.domain.DriverDomain;
import com.freenow.dto.DriverDTO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper {

    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDomain> drivers) {
        return drivers.stream()
                .map(DriverDTO::new)
                .collect(Collectors.toList());
    }
}
