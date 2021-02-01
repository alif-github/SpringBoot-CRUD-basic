package com.project.simplecrud.model;

import java.util.Date;
import java.util.List;

public class Cart {

    private String idCart;
    private Date transactionDate;
    private int idCustomer;
    private boolean status;
    List<Product> products;

    public Cart(String idCart, Date transactionDate, int idCustomer, boolean status, List<Product> products) {
        this.idCart = idCart;
        this.transactionDate = transactionDate;
        this.idCustomer = idCustomer;
        this.status = status;
        this.products = products;
    }

    public String getIdCart() {
        return idCart;
    }

    public void setIdCart(String idCart) {
        this.idCart = idCart;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "idCart='" + idCart + '\'' +
                ", transactionDate=" + transactionDate +
                ", idCustomer=" + idCustomer +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}
