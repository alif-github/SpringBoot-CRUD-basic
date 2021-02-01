package com.project.simplecrud.model;

public class Product {
    private String idProduct;
    private String product;
    private long idCategory;
    private double price;

    public Product() {

    }

    public Product(String idProduct,String product, long idCategory, double price) {
        this.idProduct = idProduct;
        this.product = product;
        this.idCategory = idCategory;
        this.price = price;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Product product1 = (Product) o;
        if (idCategory == product1.idCategory)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct='" + idProduct + '\'' +
                ", product='" + product + '\'' +
                ", idCategory=" + idCategory +
                ", price=" + price +
                '}';
    }
}
