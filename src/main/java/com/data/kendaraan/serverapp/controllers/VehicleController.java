package com.data.kendaraan.serverapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.data.kendaraan.serverapp.models.Vehicles;
import com.data.kendaraan.serverapp.services.VehicleService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

    @GetMapping
    public String getVehicles(Model model) {
        List<Vehicles> vehicle = vehicleService.getAllVehicles();
        model.addAttribute("vehicle", vehicle);
        return "vehicle/index";
    }

    // @GetMapping("/detail/{registrationNumber}")
    // public String detail(@PathVariable String registrationNumber, Model model) {
    //     model.addAttribute("vehicle", vehicleService.getVehicleById(registrationNumber));
    //     return "vehicle/detail";
    // }
    
    // @GetMapping("/create")
    // public String create(Model model) {
    //     model.addAttribute("vehicle", new Vehicles());
    //     model.addAttribute("isActive", "vehicle");
    //     model.addAttribute("availableColors", vehicleService.getAvailableColors());
    //     return "vehicle/create-form";
    // }    

    // @PostMapping
    // public String createVehicle(@ModelAttribute Vehicles vehicle) {
    //     vehicleService.saveVehicle(vehicle);
    //     return "redirect:/vehicle";
    // }

    // @GetMapping("/update/{registrationNumber}")
    // public String showUpdateForm(Model model, @PathVariable String registrationNumber) {
    //     model.addAttribute("isActive", "vehicle");
    //     model.addAttribute("vehicle", vehicleService.getVehicleById(registrationNumber));
    //     model.addAttribute("availableColors", vehicleService.getAvailableColors());
    //     return "vehicle/update-form";
    // }
    
    // @PutMapping("/{registrationNumber}")
    // public String updateVehicle(@PathVariable String registrationNumber, @ModelAttribute  Vehicles vehicle) {
    //     vehicleService.update(registrationNumber, vehicle);
    //     return "redirect:/vehicle";
    // }
    
    // @DeleteMapping("/{registrationNumber}")
    // public String delete(@PathVariable String registrationNumber) {
    //     vehicleService.deleteVehicle(registrationNumber);
    //     return "redirect:/vehicle";
    // }
}