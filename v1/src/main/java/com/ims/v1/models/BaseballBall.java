package com.ims.v1.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("BALL")
@Table(name="ball")
public class BaseballBall extends Product{

    @Column
    private double weight;
    
    @Column
    private String ballType; // Training, Game, Rubber

    @Column
    private String brand;

    @Column
    private float price;

    // placeholder
    // new field

    public BaseballBall () {

    }

    public BaseballBall (double weight, String brand, float price) {
        this.weight = weight;
        this.brand = brand;
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getBallType() {
        return ballType;
    }

    public void setBallType(String ballType) {
        this.ballType = ballType;
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
        long temp;
        temp = Double.doubleToLongBits(weight);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((ballType == null) ? 0 : ballType.hashCode());
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
        BaseballBall other = (BaseballBall) obj;
        if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
            return false;
        if (ballType == null) {
            if (other.ballType != null)
                return false;
        } else if (!ballType.equals(other.ballType))
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
        return "BaseballBall [weight=" + weight + ", ballType=" + ballType + ", brand=" + brand + ", price=" + price + "]";
    }

    
}
