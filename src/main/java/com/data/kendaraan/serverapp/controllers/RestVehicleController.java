package com.data.kendaraan.serverapp.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.data.kendaraan.serverapp.dtos.request.CreateVehicle;
import com.data.kendaraan.serverapp.dtos.request.UpdateVehicle;
import com.data.kendaraan.serverapp.dtos.response.ResponseData;
import com.data.kendaraan.serverapp.dtos.response.VehicleResponse;
import com.data.kendaraan.serverapp.models.Vehicles;
import com.data.kendaraan.serverapp.services.VehicleService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/vehicle")
public class RestVehicleController {

  private VehicleService vehicleService;

  @GetMapping
  public List<Vehicles> getAll() {
    return vehicleService.getAllVehicles();
  }

  @GetMapping("/{registrationNumber}")
  public Vehicles getById(@PathVariable String registrationNumber) {
    return vehicleService.getVehicleById(registrationNumber);
  }

  @PostMapping
  public ResponseEntity<ResponseData<VehicleResponse>> create(@Valid @RequestBody CreateVehicle request) {
      ResponseData<VehicleResponse> responseData = new ResponseData<>();
      try {
          VehicleResponse vehicleResponse = vehicleService.saveVehicle(request);
          responseData.setStatus("success");
          responseData.setData(vehicleResponse);
          responseData.setMessages(null);
          return ResponseEntity.ok(responseData);
      } catch (ResponseStatusException ex) {
          responseData.setStatus("error");
          responseData.setMessages(ex.getReason());
          return ResponseEntity.status(ex.getStatusCode()).body(responseData);
      } catch (Exception ex) {
          responseData.setStatus("error");
          responseData.setMessages("An unexpected error occurred: " + ex.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
      }
  }
  
  @PutMapping("/{registrationNumber}")
  public ResponseEntity<ResponseData<VehicleResponse>> create( @PathVariable String registrationNumber, @Valid @RequestBody UpdateVehicle request) {
    ResponseData<VehicleResponse> responseData = new ResponseData<>();
    try {
        VehicleResponse vehicleResponse = vehicleService.update(registrationNumber, request);
        responseData.setStatus("success");
        responseData.setData(vehicleResponse);
        responseData.setMessages(null);
        return ResponseEntity.ok(responseData);
    } catch (ResponseStatusException ex) {
        responseData.setStatus("error");
        responseData.setMessages(ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(responseData);
    } catch (Exception ex) {
        responseData.setStatus("error");
        responseData.setMessages("An unexpected error occurred: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseData);
    }
  }

  @DeleteMapping("/{registrationNumber}")
  public void delete(@PathVariable String registrationNumber) {
    vehicleService.deleteVehicle(registrationNumber);
  }
}
