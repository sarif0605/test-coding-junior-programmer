package com.data.kendaraan.serverapp.dtos.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVehicle {
  @NotBlank(message = "Registration Number is required")
  private String registrationNumber;
  @NotBlank(message = "Owner name is required")
  private String ownerName;

  @NotBlank(message = "address is required")
  private String address;

  @NotBlank(message = "brand is required")
  private String brand;

  @NotNull(message = "Manufacturing year is required")
  @Min(value = 1900, message = "Year must be greater than 1900")
  @Max(value = 9999, message = "Year must be a 4-digit number")
  private Integer manufacturingYear;

  @NotNull(message = "Cylinder Capacity is required")
  private Integer cylinderCapacity;

  @NotBlank(message = "Color is required")
  private String color;

  @NotBlank(message = "Fuel Type is required")
  private String fuelType;
}
