/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author hii
 */
public class User {
//     userID int primary key,
// [name] nvarchar(30),
//[address] nvarchar(50),
//[email] nvarchar(50),
//[phone] varchar(20),
//[userName] nvarchar(20),
//[password] nvarchar(20),
//[roleID] int,
    private int userID;
    private String name, address, email,phone, userName,password;
    private int roleID;
    
    public User() {
    }

    public User(int userID, String name, String address, String email, String phone, String userName, String password, int roleID) {
        this.userID = userID;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.userName = userName;
        this.password = password;
        this.roleID = roleID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }
    
}
