package com.example.stylez;

public class Product {
    private int productid;
    private String productname;
    private String gender;
    private String type;
    private double price;

    public Product(int productid, String productname, String gender, String type, double price) {
        this.productid = productid;
        this.productname = productname;
        this.gender = gender;
        this.type = type;
        this.price = price;
    }


    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
