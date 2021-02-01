package com.project.simplecrud.repository;

import com.project.simplecrud.model.Cart;
import com.project.simplecrud.model.Category;

import java.util.List;

public interface CartRepository {

    //Place for CRUD and Manipulate the Data

    //Create the Data----------------------------------------
    void saveCart(Cart cart);

    //Read the Data------------------------------------------

    //Read All Data
    List<Cart> findAllCart();

    //Read Data After Create
    List<Cart> findAllCartSave();

    //Read the Data with paggination
    List<Cart> findAllCartwPaggination(int page, int limit);

    //Read the Data by id
    Cart findByIdCart(String idCart);

    //Read the Data by name
    Category findByidCustomer(int idCustomer);

    //Read the Data by Parameter (id,name,allId and name)
//    Category findByIdAndNameCategory(long idCategory, String category);

    //Update the Data----------------------------------------
    //Update Cart Detail-------------------------------------
    void updateCartDetail(Cart cart);

    //Update Cart (Status)-----------------------------------
    void updateCartStatus(Cart cart);

    //Delete the Data----------------------------------------

    //Delete All Data
    void deleteAllCart();

    //Delete the Data (1 Data) by id
    void deleteByIdCart(String idCart);

    //Delete the Data (1 Data) by name
    void deleteByidCustomer(int idCustomer);
}
