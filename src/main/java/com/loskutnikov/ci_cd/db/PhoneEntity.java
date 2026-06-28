package com.loskutnikov.ci_cd.db;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "phones", schema = "public")
public class PhoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "brand_name")
    String brandName;
    String model;
    BigDecimal price;

    public PhoneEntity() {
    }

    public PhoneEntity(Long id, String brandName, String model, BigDecimal price) {
        this.id = id;
        this.brandName = brandName;
        this.model = model;
        this.price = price;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
