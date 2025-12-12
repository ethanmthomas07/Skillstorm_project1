package com.ims.v1.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ims.v1.models.Product;
import com.ims.v1.services.ProductService;

@RestController
@RequestMapping("/Products")
@CrossOrigin("*") // i know this is a security issue but not worried rn
public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Get all products
    @GetMapping
    public ResponseEntity<List<Product>> findAllProducts(@RequestParam(required = false) String param) {
        // try{
        //     List<Product> products = productService.findAllProducts();
        //     return new ResponseEntity<>(products, HttpStatus.OK);
        // } catch (Exception e) {
        //     return ResponseEntity.internalServerError().header("Message", "Opps something went wrong").build();
        // }
        return new ResponseEntity<>(productService.findAllProducts(), HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/id/{id}")
    public ResponseEntity<Product> findProductById (@PathVariable int id) {
        try {
            Product product = productService.findProductById(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    // Get product by Name
    @GetMapping("/name/{name}")
    public ResponseEntity<Product> findProductByName (@PathVariable String name) {
        try {
            Product product = productService.findProductByName(name);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Product>> findProductByType (@PathVariable String type) {
        try {
            List<Product> products = productService.findProductByType(type);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // ID provided invalid therefore send 400 error
            return ResponseEntity.badRequest().header("Message", e.getMessage()).build();
        } catch (Exception e) {
            // our fault therefore 500 error
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
    }


    // Create a new product (any subtype)
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        try{
            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops, something went wrong").build();
        }
        
    }

    // Update product
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product request) {
        try {
            productService.updateProduct(id, request);
            // return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
            return ResponseEntity.ok(productService.updateProduct(id, request));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().header("Message", "Oops something went wrong").build();
        }
    }

    // Delete product
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable int id) {
        Product product = productService.findProductById(id);
        productService.deleteProduct(product);
    }
}
