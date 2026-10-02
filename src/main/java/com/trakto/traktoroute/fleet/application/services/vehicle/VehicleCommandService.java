package com.trakto.traktoroute.fleet.application.services.vehicle;

import com.trakto.traktoroute.fleet.application.commands.vehicle.ActivateVehicleCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehicleCapacityCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehiclePlateNumberCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.CreateVehicleCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.DeactivateVehicleCommand;
import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.domain.repositories.VehicleRepository;
import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.application.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleCommandService {

    private final VehicleRepository vehicleRepository;

    public VehicleCommandService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public Result<Vehicle, ApplicationError> handle(CreateVehicleCommand command) {

        if (vehicleRepository.existsByPlateNumber(command.plateNumber())) {
            return Result.failure(
                    ApplicationError.conflict(
                            "Vehicle",
                            "Plate number is already registered"
                    )
            );
        }

        Vehicle vehicle = Vehicle.create(
                command.plateNumber(),
                command.capacity()
        );

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Result.success(savedVehicle);
    }

    @Transactional
    public Result<Vehicle, ApplicationError> handle(
            ChangeVehiclePlateNumberCommand command) {

        var vehicleOptional = vehicleRepository.findById(command.vehicleId());

        if (vehicleOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound(
                            "Vehicle",
                            command.vehicleId().value().toString()
                    )
            );
        }

        Vehicle vehicle = vehicleOptional.get();

        if (vehicle.getPlateNumber().equals(command.plateNumber())) {
            return Result.success(vehicle);
        }

        if (vehicleRepository.existsByPlateNumber(command.plateNumber())) {
            return Result.failure(
                    ApplicationError.conflict(
                            "Vehicle",
                            "Plate number is already registered"
                    )
            );
        }

        vehicle.changePlateNumber(command.plateNumber());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Result.success(savedVehicle);
    }

    @Transactional
    public Result<Vehicle, ApplicationError> handle(
            ChangeVehicleCapacityCommand command) {

        var vehicleOptional = vehicleRepository.findById(command.vehicleId());

        if (vehicleOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound(
                            "Vehicle",
                            command.vehicleId().value().toString()
                    )
            );
        }

        Vehicle vehicle = vehicleOptional.get();

        if (vehicle.getCapacity().equals(command.capacity())) {
            return Result.success(vehicle);
        }

        vehicle.changeCapacity(command.capacity());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Result.success(savedVehicle);
    }

    @Transactional
    public Result<Vehicle, ApplicationError> handle(ActivateVehicleCommand command) {

        var vehicleOptional = vehicleRepository.findById(command.vehicleId());

        if (vehicleOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound(
                            "Vehicle",
                            command.vehicleId().value().toString()
                    )
            );
        }

        Vehicle vehicle = vehicleOptional.get();

        try {
            vehicle.activate();
        } catch (IllegalStateException e) {
            return Result.failure(
                    ApplicationError.businessRuleViolation(
                            "Vehicle activation",
                            e.getMessage()
                    )
            );
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Result.success(savedVehicle);
    }

    @Transactional
    public Result<Vehicle, ApplicationError> handle(
            DeactivateVehicleCommand command) {

        var vehicleOptional = vehicleRepository.findById(command.vehicleId());

        if (vehicleOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound(
                            "Vehicle",
                            command.vehicleId().value().toString()
                    )
            );
        }

        Vehicle vehicle = vehicleOptional.get();

        try {
            vehicle.deactivate();
        } catch (IllegalStateException e) {
            return Result.failure(
                    ApplicationError.businessRuleViolation(
                            "Vehicle deactivation",
                            e.getMessage()
                    )
            );
        }

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        return Result.success(savedVehicle);
    }
}