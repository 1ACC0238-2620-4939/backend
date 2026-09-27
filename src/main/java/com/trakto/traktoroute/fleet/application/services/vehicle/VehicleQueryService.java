package com.trakto.traktoroute.fleet.application.services.vehicle;

import com.trakto.traktoroute.fleet.application.queries.vehicle.GetAllVehiclesQuery;
import com.trakto.traktoroute.fleet.application.queries.vehicle.GetVehicleByIdQuery;
import com.trakto.traktoroute.fleet.application.queries.vehicle.GetVehiclesByStatusQuery;
import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.domain.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleQueryService {

    private final VehicleRepository vehicleRepository;

    public VehicleQueryService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public Optional<Vehicle> handle(GetVehicleByIdQuery query) {
        return vehicleRepository.findById(query.vehicleId()
        );
    }

    public List<Vehicle> handle(GetAllVehiclesQuery query) {
        return vehicleRepository.findAll();
    }

    public List<Vehicle> handle(GetVehiclesByStatusQuery query) {
        return vehicleRepository.findByStatus(query.status()
        );
    }
}