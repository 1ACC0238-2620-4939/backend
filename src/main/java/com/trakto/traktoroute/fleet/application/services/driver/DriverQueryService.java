package com.trakto.traktoroute.fleet.application.services.driver;

import com.trakto.traktoroute.fleet.application.queries.driver.GetAllDriversQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriverByIdQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriverByProfileIdQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriversByStatusQuery;
import com.trakto.traktoroute.fleet.domain.model.aggregates.Driver;
import com.trakto.traktoroute.fleet.domain.repositories.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DriverQueryService {

    private final DriverRepository driverRepository;

    public DriverQueryService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Optional<Driver> handle(GetDriverByIdQuery query) {
        return driverRepository.findById(query.driverId()
        );
    }

    public Optional<Driver> handle(GetDriverByProfileIdQuery query) {
        return driverRepository.findByProfileId(query.profileId()
        );
    }

    public List<Driver> handle(GetAllDriversQuery query) {
        return driverRepository.findAll();
    }

    public List<Driver> handle(GetDriversByStatusQuery query) {
        return driverRepository.findByStatus(query.status()
        );
    }
}