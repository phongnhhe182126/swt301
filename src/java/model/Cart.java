/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hii
 */
public class Cart {
    
    private List<Item> items;
    
    public Cart() {
        items = new ArrayList<>();
    }
    
    public Cart(List<Item> items) {
        this.items = items;
    }
    
    public List<Item> getItems() {
        return items;
    }
    
    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public Item getItemByID(String id) {
        for (Item i : items) {
            if (i.getProduct().getProductID().equals(id)) {
                return i;
            }
        }
        return null;
    }
    
    public int getQuantityByID(String id) {
        return getItemByID(id).getQuantity();
        
    }
    
    public void addItem(Item i) {
        //th co trong database r thi update
        if (getItemByID(i.getProduct().getProductID()) != null) {
            Item item = getItemByID(i.getProduct().getProductID());
            
            item.setQuantity(i.getQuantity() + item.getQuantity());
        } else {
            items.add(i);
        }
    }

    public void addItemMody(Item i) {
        //th co trong database r thi update
        if (getItemByID(i.getProduct().getProductID()) != null) {
            Item item = getItemByID(i.getProduct().getProductID());
            if (item.getQuantity() > 4) {
                item.setQuantity(2 + item.getQuantity());
            } else {
                item.setQuantity(1 + item.getQuantity());
            }
        } else {
            items.add(i);
        }
    }
    
    public void removeItem(String id) {
        if (getItemByID(id) != null) {
            items.remove(getItemByID(id));
        }
    }
    
    public double getTotalMoney() {
        double total = 0;
        
        for (Item item : items) {
            total += item.getQuantity() * item.getPrice();
        }
        return total;
        
    }
    
}
