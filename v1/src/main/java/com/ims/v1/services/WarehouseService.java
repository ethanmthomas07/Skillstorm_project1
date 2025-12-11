package com.ims.v1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ims.v1.models.Warehouse;
import com.ims.v1.repositories.WarehouseRepository;

@Service
public class WarehouseService {

    // Constructor Injection
    private final WarehouseRepository warehouseRepository;

    public WarehouseService(WarehouseRepository warehouseRepository){
        this.warehouseRepository = warehouseRepository;
    }
    
    public List<Warehouse> findByMinimumCapacity(int capacity) {
        return warehouseRepository.findByCapacityLessThanEqual(capacity);
        
    }

    public Warehouse findWarehouseById(int id) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(id);
        if (warehouse.isPresent()) {
            return warehouse.get(); // returns the object from the optional
        } else {
            throw new IllegalArgumentException("No Warehouse with that ID: " + id);
        }
    }

    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
        
    }

    public List<Warehouse> findByMaximumCapacity(int capacity) {
        return warehouseRepository.findByCapacityGreaterThanEqual(capacity);
    }

    public Warehouse findWarehouseByName(String name) {
        return warehouseRepository.findByName(name);
        
    }

    public Warehouse createWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
        
    }

    public void updateCapacity(int id, int newCapacity) {
        warehouseRepository.updateCapacity(id, newCapacity);
    }

    public void updateName(int id, String newName) {
        warehouseRepository.updateName(id, newName);
    }

    public void updateLocation(int id, String newLocation) {
        warehouseRepository.updateLocation(id, newLocation);
    }

    public void deleteWarehouse(int id) {
        warehouseRepository.deleteById(id);
    }
    
}
