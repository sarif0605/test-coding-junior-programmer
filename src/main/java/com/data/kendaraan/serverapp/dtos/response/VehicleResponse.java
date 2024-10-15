package com.data.kendaraan.serverapp.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleResponse {
  private String registrationNumber;
  private String ownerName;
  private String address;
  private String brand;
  private Integer manufacturingYear;
  private Integer cylinderCapacity;
  private String color;
  private String fuelType;
}
