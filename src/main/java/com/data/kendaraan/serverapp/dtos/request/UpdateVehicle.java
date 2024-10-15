package com.data.kendaraan.serverapp.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateVehicle {
  @NotNull(message = "Registration Number is null")
  private String registrationNumber;
  @NotNull(message = "Owner name is null")
  private String ownerName;

  @NotNull(message = "address is null")
  private String address;

  @NotNull(message = "brand is null")
  private String brand;

  @NotNull(message = "Manufacturing year is null")
  @Min(value = 1900, message = "Year must be greater than 1900")
  @Max(value = 9999, message = "Year must be a 4-digit number")
  private Integer manufacturingYear;

  @NotNull(message = "Cylinder Capacity is null")
  private Integer cylinderCapacity;

  @NotNull(message = "Color is null")
  private String color;

  @NotNull(message = "Fuel Type is null")
  private String fuelType;
}
