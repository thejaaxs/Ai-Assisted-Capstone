package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    // ============================
    // ADD VEHICLE
    // ============================
    @PostMapping
    public Vehicle add(@RequestBody Vehicle vehicle) {
        return service.addVehicle(vehicle);
    }

    // ============================
    // GET ALL VEHICLES
    // ============================
    @GetMapping
    public List<Vehicle> getAll() {
        return service.getAllVehicles();
    }

    // ============================
    // GET VEHICLE BY ID  ✅ ADDED
    // ============================
    @GetMapping("/{id}")
    public Vehicle getById(@PathVariable Long id) {
        return service.getVehicleById(id);
    }

    // ============================
    // GET VEHICLES BY DEALER
    // ============================
    @GetMapping("/dealer/{dealerId}")
    public List<Vehicle> getDealerVehicles(@PathVariable Long dealerId) {
        return service.getByDealer(dealerId);
    }

    // ============================
    // UPDATE VEHICLE
    // ============================
    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id,
                          @RequestBody Vehicle vehicle) {
        return service.updateVehicle(id, vehicle);
    }

    // ============================
    // DELETE VEHICLE
    // ============================
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteVehicle(id);
        return "Deleted Successfully";
    }
}