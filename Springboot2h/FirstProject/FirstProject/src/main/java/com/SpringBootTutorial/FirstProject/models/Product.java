package com.SpringBootTutorial.FirstProject.models;


import jakarta.persistence.*;

@Entity
@Table(name = "tblProduct")
public class Product {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
   @SequenceGenerator(
           name = "product_sequence",
           sequenceName = "product_sequence",
           allocationSize = 1 //tang 1
   )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    //validate
    @Column(nullable = false,unique = true,length = 300)
    private String productName;
    private int year;
    private Double price;
    private String url;

    public Product() {}

    //caculated field = transient
    @Transient
    private int age;

    public Product(String productName, int year, Double price, String url) {
        this.productName = productName;
        this.year = year;
        this.price = price;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
