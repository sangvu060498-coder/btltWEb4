package com.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
@NamedQueries({
    @NamedQuery(name = "Product.findAll", query = "SELECT p FROM Product p ORDER BY p.productId DESC"),
    @NamedQuery(name = "Product.count", query = "SELECT COUNT(p) FROM Product p")
})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductId")
    private int productId;

    @Column(name = "ProductName", columnDefinition = "nvarchar(255) not null")
    private String productName;

    @Column(name = "Description", columnDefinition = "nvarchar(max) null")
    private String description;

    @Column(name = "Price")
    private double price;

    @Column(name = "Images", columnDefinition = "nvarchar(500) null")
    private String images;

    @Column(name = "Status")
    private int status; // 1: Active, 0: Inactive

    @Column(name = "CreatedDate")
    private LocalDateTime createdDate = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public Product() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public LocalDateTime getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDateTime createdDate) { this.createdDate = createdDate; }
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}