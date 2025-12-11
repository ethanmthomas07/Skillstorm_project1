package com.ims.v1.models;



import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("BAT")
@Table(name="Bats")
public class BaseballBat extends Product{
    
    @Column
    private int length;
    
    @Column
    private int weight;

    @Column
    private String brand;

    @Column 
    private float price;

    // placeholder
    // new field

    public BaseballBat() {

    }

    public BaseballBat(int length, int weight, String brand, float price) {
        this.length = length; 
        this.weight = weight;
        this.brand = brand;
        this.price = price;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + length;
        result = prime * result + weight;
        result = prime * result + ((brand == null) ? 0 : brand.hashCode());
        result = prime * result + Float.floatToIntBits(price);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseballBat other = (BaseballBat) obj;
        if (length != other.length)
            return false;
        if (weight != other.weight)
            return false;
        if (brand == null) {
            if (other.brand != null)
                return false;
        } else if (!brand.equals(other.brand))
            return false;
        if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseballBat [length=" + length + ", weight=" + weight + ", brand=" + brand + ", price=" + price + "]";
    }
    

    
}
