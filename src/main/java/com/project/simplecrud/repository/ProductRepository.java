package com.project.simplecrud.repository;

import com.project.simplecrud.model.Product;

import java.util.List;

public interface ProductRepository {

    //Place for CRUD and Manipulate the Data

    //Create the Data----------------------------------------
    void saveProduct(Product product);

    //Read the Data------------------------------------------

    //Read All Data
    List<Product> findAllProduct();

    //Read Data After Create
    List<Product> findAllProductSave();

    //Read the Data by id
    Product findByIdProduct(String idProduct);

    //Read the Data by name
    List<Product> findByNameProduct(String product);

    //Read the Data by Parameter (id,name,allId and name)
    Product findByIdAndNameProduct(String idProduct, String product);

    //Update the Data----------------------------------------
    void updateDataProduct(String idProduct,Product product);

    //Delete the Data----------------------------------------

    //Delete All Data
    void deleteAllProduct();

    //Delete the Data (1 Data) by id
    void deleteByIdProduct(String idProduct);

    //Delete the Data (1 Data) by name
    void deleteByNameProduct(String product);
}
