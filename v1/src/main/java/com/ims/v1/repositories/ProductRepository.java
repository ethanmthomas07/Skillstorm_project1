package com.ims.v1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ims.v1.models.Product;

import jakarta.transaction.Transactional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    @Query("Select * from Products p where p.name = :name")
    Optional<Product> findProductByName(@Param("productName") String name);

    @Query("Select * from Products p where p.tpye = :type")
    Optional<List<Product>> findProductByType(@Param("productType") String type);

    @Query("Select * from Products p where p.warehouseId = :warehouseId")
    List<Product> findByWarehouseId(@Param("warehouseId") int warehouseId);

    // allegedly dont need update methods due to type subtype structure and .save() handles it

    /*
    Finds all fields for movies with a rating greater than or equal to the specified rating passed to the method.
    */
    // public List<Movie> findAllByRatingGreaterThanEqual(int rating);

    // @Query("update Movie m set m.title = :movieTitle where id = :movieId") // takes in some string to be used as a JPQL query - basically write your own queries
    // @Modifying // needed for update, insert, delete queries, specifies that this query is not a select and is changing data
    // @Transactional // needed for modifying queries to ensure data integrity - solves the bank transfer problem (all work or no work)
    // public int updateMovieTitle(@Param("movieTitle") String title, @Param("movieId") int id);

    
    
}
