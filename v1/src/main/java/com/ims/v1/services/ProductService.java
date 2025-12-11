package com.ims.v1.services;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;

import com.ims.v1.models.BaseballBall;
import com.ims.v1.models.BaseballBat;
import com.ims.v1.models.BaseballGlove;
import com.ims.v1.models.BaseballHat;
import com.ims.v1.models.Product;
import com.ims.v1.models.Warehouse;
import com.ims.v1.repositories.ProductRepository;
import com.ims.v1.repositories.WarehouseRepository;

public class ProductService {

    private final ProductRepository productRepository;
    private final WarehouseRepository warehouseRepository;
    public ProductService(ProductRepository productRepository, WarehouseRepository warehouseRepository){
        this.productRepository = productRepository;
        this.warehouseRepository = warehouseRepository;
    }

    public Product findProductById(int id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return product.get(); // returns the object from the optional
        } else {
            throw new IllegalArgumentException("No Warehouse with that ID: " + id);
        }
    }

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    public Product findProductByName(String name) {
        return productRepository.findProductByName(name).orElseThrow(() -> new RuntimeException("Not found"));
    }

    // removes product from warehouse and product table
    public void deleteProduct(Product product) {
        int prodIndex = product.getWarehouse().getProducts().lastIndexOf(product);
        product.getWarehouse().getProducts().remove(prodIndex);
        productRepository.deleteById(product.getProductId()); 
    }

    public List<Product> findProductByType(String type) {
        return productRepository.findProductByType(type).orElseThrow(() -> new RuntimeException("Not found"));
        
    }

    public Product createProduct(Product product) {
        product.setProductId(0);
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updated) {
        Product product = productRepository.findProductById(id);
        //.orElseThrow(() -> new NotFoundException("Product not found"));
        if (product != null){
            
            // Update common fields
            if (updated.getName() != null) product.setName(updated.getName());
            if (updated.getProductType() != null) product.setProductType(updated.getProductType());

            // Handle BaseballBall subtype
            if (product instanceof BaseballBall baseball && updated instanceof BaseballBall updatedBall) {
               baseball.setWeight(updatedBall.getWeight());
               baseball.setBrand(updatedBall.getBrand());
               baseball.setBallType(updatedBall.getBallType());
               baseball.setPrice(updatedBall.getPrice());
            }
            // Handle BaseballBat subtype
            else if (product instanceof BaseballBat bat && updated instanceof BaseballBat updatedBat) {
                bat.setBrand(updatedBat.getBrand());
                bat.setWeight(updatedBat.getWeight());
                bat.setLength(updatedBat.getLength());
                bat.setPrice(updatedBat.getPrice());
            }
            // Handle BaseballGlove subtype
            else if (product instanceof BaseballGlove glove && updated instanceof BaseballGlove updatedGlove) {
                glove.setBrand(updatedGlove.getBrand());
                glove.setPrice(updatedGlove.getPrice());
                glove.setColor(updatedGlove.getColor());
                glove.setSize(updatedGlove.getSize());
            }
            // Handle BaseballHat subtype
            else if (product instanceof BaseballHat hat && updated instanceof BaseballHat updatedHat) {
                hat.setColor(updatedHat.getColor());
                hat.setPrice(updatedHat.getPrice());
                hat.setSize(updatedHat.getSize());
            }
            return productRepository.save(product);
        }else{
            throw new NullPointerException("Product Not Found 'issue in method updateProduct'");
        }

        
    }

    // This may be bad practice
    public Product addProductToWarehouse(Product product, int warehouseId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        try{
            warehouse.addProduct(product);
            product.setWarehouse(warehouse);
            // return productRepo.save(product);
        } catch (Exception e) {
            System.err.println(e);
        }
        
        return productRepository.save(product);
    }
    
}
