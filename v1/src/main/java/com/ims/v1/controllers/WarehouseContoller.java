package com.ims.v1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ims.v1.models.Warehouse;
import com.ims.v1.services.WarehouseService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/warehouses")
@CrossOrigin("http://127.0.0.1:5500/warehouses")
public class WarehouseContoller {
    
    private final WarehouseService warehouseService;
    public WarehouseContoller(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAllWarehouses (@RequestParam String param) {
        try{
            List<Warehouse> warehouses = warehouseService.findAllWarehouses();
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Opps something went wrong").build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Warehouse> findWarehouseById (@PathVariable int id) {
        try {
            Warehouse warehouse = warehouseService.findWarehouseById(id);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<Warehouse>> findByMinimumCapacity(@PathVariable int capacity) {
        try {
            List<Warehouse> warehouses = warehouseService.findByMinimumCapacity(capacity);
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @GetMapping("/capacity/{capacity}")
    public ResponseEntity<List<Warehouse>> findByMaximumCapacity(@PathVariable int capacity) {
        try {
            List<Warehouse> warehouses = warehouseService.findByMaximumCapacity(capacity);
            return new ResponseEntity<>(warehouses, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Warehouse> findWarehouseByName (@PathVariable String name) {
        try {
            Warehouse warehouse = warehouseService.findWarehouseByName(name);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<Warehouse> findWarehouseByLocation (@PathVariable String location) {
        try {
            Warehouse warehouse = warehouseService.findWarehouseByName(location);
            return new ResponseEntity<>(warehouse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @PostMapping
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse) {
        try{
            return new ResponseEntity<>(warehouseService.createWarehouse(warehouse), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
        
    }

    @PutMapping("/capacity/{id}")
    public ResponseEntity<Warehouse> updateWarehouseCapacity(@PathVariable int id, @RequestBody int newCapacity) {
        try {
            warehouseService.updateCapacity(id, newCapacity);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops something went wrong").build();
        }
    }

    @PutMapping("/name/{id}")
    public ResponseEntity<Warehouse> updateWarehouseName(@PathVariable int id, @RequestBody String newName) {
        try {
            warehouseService.updateName(id, newName);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops something went wrong").build();
        }
    }

    @PutMapping("/location/{id}")
    public ResponseEntity<Warehouse> updateWarehouseLocation(@PathVariable int id, @RequestBody String newLocation) {
        try {
            warehouseService.updateLocation(id, newLocation);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops something went wrong").build();
        }
    }

    // Delete product
    @DeleteMapping("/id/{id}")
    public void deleteWarehouse(@PathVariable int id) {
        warehouseService.deleteWarehouse(id);
    }
    
}
