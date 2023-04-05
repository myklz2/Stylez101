package com.example.stylez;

public class Order {
    private int ordertid;
    private int userid;
    private int productid;
    private int numofproducts;
    private double totalprice;

    public Order(int ordertid, int userid, int productid, int numofproducts, double totalprice) {
        this.ordertid = ordertid;
        this.userid = userid;
        this.productid = productid;
        this.numofproducts = numofproducts;
        this.totalprice = totalprice;
    }


    public int getOrdertid() {
        return ordertid;
    }

    public void setOrdertid(int ordertid) {
        this.ordertid = ordertid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getNumofproducts() {
        return numofproducts;
    }

    public void setNumofproducts(int numofproducts) {
        this.numofproducts = numofproducts;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }
}
