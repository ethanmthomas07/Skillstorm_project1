package com.ims.v1.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ims.v1.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

    Product findProductById(int id);

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findProductByName(String name);

    Optional<List<Product>> findProductByType(String type);

    List<Product> findByWarehouseId(int warehouseId);
    
}
