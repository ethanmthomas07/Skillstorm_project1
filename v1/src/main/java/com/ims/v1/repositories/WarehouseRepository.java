package com.ims.v1.repositories;

import com.ims.v1.models.Warehouse;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

    List<Warehouse> findByMinimumCapacity(int capacity);

    List<Warehouse> findByMaximumCapacity(int capacity);

    Warehouse findByName(String name);

    Optional<Warehouse> updateCapacity(int id, int newCapacity);

    void updateName(int id, String newName);

    void updateLocation(int id, String newLocation);

    
    
}
