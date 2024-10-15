package com.data.kendaraan.serverapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.data.kendaraan.serverapp.models.Vehicles;
import java.util.Optional;

@Repository
public interface VehiclesRepository extends JpaRepository<Vehicles, String> {

  Optional<Vehicles> findByRegistrationNumber(String registrationNumber);
  Boolean existsByRegistrationNumber(String registrationNumber);
}
