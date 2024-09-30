/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hii
 */
public class Product {

  private String productID, productName,description,image;
  private double price;
  private int quantity;
  private Category productCategoryID;

    public Product() {
    }

    public Product(String productID, String productName, String description, String image, double price, int quantity, Category productCategoryID) {
        this.productID = productID;
        this.productName = productName;
        this.description = description;
        this.image = image;
        this.price = price;
        this.quantity = quantity;
        this.productCategoryID = productCategoryID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Category getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(Category productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    
  
}
