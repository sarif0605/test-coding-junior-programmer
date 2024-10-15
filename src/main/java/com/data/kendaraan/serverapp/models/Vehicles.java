package com.data.kendaraan.serverapp.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_vehicles")
public class Vehicles {
    @Id
    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "owner_name")
    private String ownerName;

    @Column(name = "address")
    private String address;

    @Column(name = "brand")
    private String brand;

    @Column(name = "manufacturing_year")
    private Integer manufacturingYear;

    @Column(name = "cylinder_capacity")
    private Integer cylinderCapacity;

    @Column(name = "color")
    private String color;

    @Column(name = "fuel_type")
    private String fuelType;
}