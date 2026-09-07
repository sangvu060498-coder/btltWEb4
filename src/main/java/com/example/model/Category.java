package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "categories")
@NamedQuery(name = "Category.findAll", query = "SELECT c FROM Category c")
public class Category implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CategoryId")
    private int categoryid;

    @Column(name = "CategoryName", columnDefinition = "nvarchar(200) not null")
    @NotEmpty(message = "Không được phép rỗng")
    private String categoryname;

    @Column(name = "Images", columnDefinition = "nvarchar(500) null")
    private String images;

    @Column(name = "Status")
    private int status;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Video> videos;
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> products;
    
    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
    public Category() {}

    public Category(int categoryid, String categoryname, String images, int status, List<Video> videos) {
        this.categoryid = categoryid;
        this.categoryname = categoryname;
        this.images = images;
        this.status = status;
        this.videos = videos;
    }

    public int getCategoryid() { return categoryid; }
    public void setCategoryid(int categoryid) { this.categoryid = categoryid; }
    public String getCategoryname() { return categoryname; }
    public void setCategoryname(String categoryname) { this.categoryname = categoryname; }
    public String getImages() { return images; }
    public void setImages(String images) { this.images = images; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public List<Video> getVideos() { return videos; }
    public void setVideos(List<Video> videos) { this.videos = videos; }

    public Video addVideo(Video video) {
        if (getVideos() == null) {
            setVideos(new ArrayList<>());
        }
        getVideos().add(video);
        video.setCategory(this);
        return video;
    }

    public Video removeVideo(Video video) {
        if (getVideos() != null) {
            getVideos().remove(video);
        }
        video.setCategory(null);
        return video;
    }
    public Product addProduct(Product product) {
        if (getProducts() == null) {
            setProducts(new ArrayList<>());
        }
        getProducts().add(product);
        product.setCategory(this);
        return product;
    }

    public Product removeProduct(Product product) {
        if (getProducts() != null) {
            getProducts().remove(product);
        }
        product.setCategory(null);
        return product;
    }
}