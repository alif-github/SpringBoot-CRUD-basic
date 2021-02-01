package com.project.simplecrud.service;

import com.project.simplecrud.model.Category;

import java.util.List;

public interface CategoryService {

    //Place for CRUD and Manipulate the Data

    //Create the Data----------------------------------------
    void saveCategory(Category category);

    //Read the Data------------------------------------------

    //Read All Data
    List<Category> findAllCategory();

    //Read Data After Create
    List<Category> findAllCategorySave();

    //Read the Data by id
    Category findByIdCategory(long idCategory);

    //Read the Data by name
    Category findByNameCategory(String category);

    //Read the Data by Parameter (id,name,allId and name)
    Category findByIdAndNameCategory(long idCategory, String category);

    //Update the Data----------------------------------------
    void updateDataCategory(long idCategory,Category category);

    //Delete the Data----------------------------------------

    //Delete All Data
    void deleteAllCategory();

    //Delete the Data (1 Data) by id
    void deleteByIdCategory(long idCategory);

    //Delete the Data (1 Data) by name
    void deleteByNameCategory(String category);

    //is Category Exist,-------------------------------------
    //to check with name of Category parameter
    boolean isCategoryExist(Category category);
}
