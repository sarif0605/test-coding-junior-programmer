package com.data.kendaraan.serverapp.services;

import java.util.List;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.data.kendaraan.serverapp.dtos.request.CreateVehicle;
import com.data.kendaraan.serverapp.dtos.request.UpdateVehicle;
import com.data.kendaraan.serverapp.dtos.response.VehicleResponse;
import com.data.kendaraan.serverapp.models.Vehicles;
import com.data.kendaraan.serverapp.repositories.VehiclesRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VehicleService {
    @Autowired
    private VehiclesRepository repository;

    @Transactional(readOnly = true)
    public List<Vehicles> getAllVehicles() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Vehicles getVehicleById(String registrationNumber) {
        return repository.findByRegistrationNumber(registrationNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vehicle with registration number " + registrationNumber + " not found"));
    }    

    @Transactional
    public Vehicles deleteVehicle(String registrationNumber) {
        Vehicles vehicles = getVehicleById(registrationNumber);
        repository.delete(vehicles);
        return vehicles;
    }

    @Transactional
    public VehicleResponse saveVehicle(CreateVehicle request) {
        Vehicles vehicle = new Vehicles();
        if (repository.existsByRegistrationNumber(request.getRegistrationNumber())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Registration Number is already exists!!!"
            );
        }
        vehicle.setRegistrationNumber(request.getRegistrationNumber());
        vehicle.setBrand(request.getBrand());
        vehicle.setOwnerName(request.getOwnerName());
        vehicle.setManufacturingYear(request.getManufacturingYear());
        vehicle.setCylinderCapacity(request.getCylinderCapacity());
        vehicle.setColor(request.getColor());
        vehicle.setFuelType(request.getFuelType());
        vehicle.setAddress(request.getAddress());
        repository.save(vehicle);
        return response(vehicle);
    }
    
    public VehicleResponse update(String registrationNumber, UpdateVehicle request) {
        Vehicles vehicles = getVehicleById(registrationNumber);
        if (request.getRegistrationNumber() != null &&
            !request.getRegistrationNumber().equalsIgnoreCase(vehicles.getRegistrationNumber())) {
            if (repository.existsByRegistrationNumber(request.getRegistrationNumber())) {
                throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Registration Number already exists!"
                );
            }
            vehicles.setRegistrationNumber(request.getRegistrationNumber());
        }
        if (request.getAddress() != null) vehicles.setAddress(request.getAddress());
        if (request.getBrand() != null) vehicles.setBrand(request.getBrand());
        if (request.getColor() != null) vehicles.setColor(request.getColor());
        if (request.getCylinderCapacity() != null) vehicles.setCylinderCapacity(request.getCylinderCapacity());
        if (request.getFuelType() != null) vehicles.setFuelType(request.getFuelType());
        if (request.getOwnerName() != null) vehicles.setOwnerName(request.getOwnerName());
        if (request.getManufacturingYear() != null) vehicles.setManufacturingYear(request.getManufacturingYear());
        repository.save(vehicles);
        return response(vehicles);
    }

    public List<String> getAvailableColors() {
        return Arrays.asList("Merah", "Hitam", "Biru", "Abu-Abu");
    }

    private VehicleResponse response(Vehicles response){
        return VehicleResponse.builder()
           .registrationNumber(response.getRegistrationNumber())
           .ownerName(response.getOwnerName())
           .manufacturingYear(response.getManufacturingYear())
           .cylinderCapacity(response.getCylinderCapacity())
           .color(response.getColor())
           .fuelType(response.getFuelType())
           .address(response.getAddress())
           .brand(response.getBrand())
           .build();
    }
}
