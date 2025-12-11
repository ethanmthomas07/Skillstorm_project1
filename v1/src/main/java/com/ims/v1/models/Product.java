package com.ims.v1.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.*;

// Needed because I am not using dtos in this project
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BaseballBall.class, name = "BALL"),
        @JsonSubTypes.Type(value = BaseballBat.class, name = "BAT"),
        @JsonSubTypes.Type(value = BaseballGlove.class, name = "GLOVE"),
        @JsonSubTypes.Type(value = BaseballHat.class, name = "HAT")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="product_type")  
@Table(name = "products")
public abstract class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    private String name;

    @Column(name = "product_type")
    private String productType;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnore // prevent infinite recursion in JSON
    private Warehouse warehouse;

    public Product () {

    }

    public Product(int productId, String name, String productType, Warehouse warehouse){
        this.productId = productId;
        this.name = name;
        this.productType = productType;
        this.warehouse = warehouse;
    }

    public Product(String name, String productType, Warehouse warehouse){
        this.name = name;
        this.productType = productType;
        this.warehouse = warehouse;
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

    public Warehouse getWarehouse() {
        return this.warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }
}

