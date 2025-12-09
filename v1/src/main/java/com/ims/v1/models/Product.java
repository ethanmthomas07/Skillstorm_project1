package com.ims.v1.models;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)  
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String name;

    @Column(name = "product_type")
    private String productType;

    public Product () {

    }

    public Product(int productId, String name, String productType){
        this.productId = productId;
        this.name = name;
        this.productType = productType;
    }

    public Product(String name, String productType){
        this.name = name;
        this.productType = productType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    
}

