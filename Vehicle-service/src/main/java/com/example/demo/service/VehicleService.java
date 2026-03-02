package com.example.demo.service;

import com.example.demo.entity.Vehicle;
import com.example.demo.repository.VehicleRepository;
import com.example.demo.client.DealerClient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class VehicleService {

    private final VehicleRepository repo;
    private final DealerClient dealerClient;

    public VehicleService(VehicleRepository repo,
                          DealerClient dealerClient) {
        this.repo = repo;
        this.dealerClient = dealerClient;
    }

    // ============================
    // ADD VEHICLE
    // ============================
    public Vehicle addVehicle(Vehicle vehicle) {

        if (vehicle.getDealerId() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Dealer ID is required"
            );
        }

        vehicle.setId(null); // prevent manual ID
        vehicle.setStatus("AVAILABLE");

        return repo.save(vehicle);
    }

    // ============================
    // GET ALL
    // ============================
    public List<Vehicle> getAllVehicles() {
        return repo.findAll();
    }

    // ============================
    // GET BY ID  ✅ ADDED
    // ============================
    public Vehicle getVehicleById(Long id) {
        return repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Vehicle not found with id: " + id
                        )
                );
    }

    // ============================
    // GET BY DEALER
    // ============================
    public List<Vehicle> getByDealer(Long dealerId) {
        return repo.findByDealerId(dealerId);
    }

    // ============================
    // UPDATE VEHICLE
    // ============================
    public Vehicle updateVehicle(Long id, Vehicle vehicle) {

        Vehicle existing = repo.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Vehicle not found with id: " + id
                        )
                );

        if (vehicle.getName() != null)
            existing.setName(vehicle.getName());

        if (vehicle.getBrand() != null)
            existing.setBrand(vehicle.getBrand());

        if (vehicle.getPrice() != null)
            existing.setPrice(vehicle.getPrice());

        if (vehicle.getDealerId() != null)
            existing.setDealerId(vehicle.getDealerId());

        if (vehicle.getStatus() != null)
            existing.setStatus(vehicle.getStatus());

        return repo.save(existing);
    }

    // ============================
    // DELETE VEHICLE
    // ============================
    public void deleteVehicle(Long id) {

        if (!repo.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vehicle not found with id: " + id
            );
        }

        repo.deleteById(id);
    }
}