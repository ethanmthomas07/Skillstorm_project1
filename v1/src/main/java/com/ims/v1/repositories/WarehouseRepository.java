package com.ims.v1.repositories;

import com.ims.v1.models.Warehouse;
import java.util.List;
import java.util.Optional;

import org.hibernate.dialect.lock.OptimisticForceIncrementLockingStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Integer>{

    // public List<Warehouse> findByMinimumCapacity(int capacity);
    public List<Warehouse> findByCapacityLessThanEqual(int capacity);

    // public List<Warehouse> findByMaximumCapacity(int capacity);
    public List<Warehouse> findByCapacityGreaterThanEqual(int capacity);

    @Query("Select * from Warehouses w where w.name = :name")
    public Warehouse findByName(@Param("warehouseName") String name);

    @Query("Update Warehouse w set w.capacity = :newCapacity where id = :id")
    @Modifying 
    @Transactional
    public Optional<Warehouse> updateCapacity(@Param("warehouseId") int id, @Param("warehouseCapacity") int newCapacity);

    @Query("Updates Warehouse w set w.name = :newName where id = :id")
    @Modifying
    @Transactional
    public void updateName(@Param("warehouseId") int id, @Param("warehouseName") String newName);

    @Query("update Warehouse w set w.location = :newLocation where id = :id")
    @Modifying
    @Transactional
    public void updateLocation(@Param("warehouseId") int id, @Param("warehouseLocation") String newLocation);
    
    /*
    Finds all fields for movies with a rating greater than or equal to the specified rating passed to the method.
    */
    // public List<Movie> findAllByRatingGreaterThanEqual(int rating);

    // @Query("update Movie m set m.title = :movieTitle where id = :movieId") // takes in some string to be used as a JPQL query - basically write your own queries
    // @Modifying // needed for update, insert, delete queries, specifies that this query is not a select and is changing data
    // @Transactional // needed for modifying queries to ensure data integrity - solves the bank transfer problem (all work or no work)
    // public int updateMovieTitle(@Param("movieTitle") String title, @Param("movieId") int id);
}
