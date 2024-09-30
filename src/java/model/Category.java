/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**category_name
 *
 * @author hii
 */
public class Category {

    private int productCategoryID;
    private String category_name;

    public Category() {
    }

    public Category(int productCategoryID, String category_name) {
        this.productCategoryID = productCategoryID;
        this.category_name = category_name;
    }

    public int getProductCategoryID() {
        return productCategoryID;
    }

    public void setProductCategoryID(int productCategoryID) {
        this.productCategoryID = productCategoryID;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
    
}
