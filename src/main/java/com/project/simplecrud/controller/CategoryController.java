package com.project.simplecrud.controller;

import com.project.simplecrud.model.Category;
import com.project.simplecrud.service.CategoryService;
import com.project.simplecrud.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    CategoryService categoryService; //Places to all manipulate data, CRUD, and work with data

    //Create Category Data--------------------------------------------------------------------
    @RequestMapping(value = "/category/", method = RequestMethod.POST)
    public ResponseEntity<?> createCategory(@RequestBody Category category) {
        logger.info("Creating Category : {}", category);

        if (categoryService.isCategoryExist(category)){
            logger.error("Unable to create Category, Category with name {} already exist", category.getCategory());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. Category with name "+category.getCategory()+" already exist."), HttpStatus.CONFLICT);
        } else {
            categoryService.saveCategory(category);
            List<Category> categories = categoryService.findAllCategorySave();
            return new ResponseEntity<>(categories, HttpStatus.CREATED);
        }
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/category/", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> showCategory() {
        logger.info("Showing Category ... ");
        List<Category> categories = categoryService.findAllCategory();

        if (categories.isEmpty()) {
            logger.error("Unable to show Category, because empty on Database");
            return new ResponseEntity<>(categories, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    //Show Data By Name-----------------------------------------------------------------------
    @RequestMapping(value = "/category/name/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleCategory(@RequestParam("name") String category) {
        logger.info("Showing Category with name {} ...", category);

        Category category1 = categoryService.findByNameCategory(category);
        if (category1 == null) {
            logger.error("Unable to show Category, because category name {} is not found", category);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Category "+category+" , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

    //Show Data By Id-------------------------------------------------------------------------
    @RequestMapping(value = "/category/id/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleCategory(@RequestParam("id") long idCategory) {
        logger.info("Showing Category with id {} ...", idCategory);

        Category category1 = categoryService.findByIdCategory(idCategory);
        if (category1 == null) {
            logger.error("Unable to show Category, because category id {} is not found", idCategory);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Category "+idCategory+" , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

    //Show Data By Id and Name----------------------------------------------------------------
    @RequestMapping(value = "/category/detail/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleCategory(@RequestParam("id") long idCategory, @RequestParam("name") String category) {
        logger.info("Showing Category with id {} name {} ...", idCategory, category);

        Category category1 = categoryService.findByIdAndNameCategory(idCategory, category);
        if (category1 == null) {
            logger.error("Unable to show Category, because category id {} and name {} is not found", idCategory, category);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Category "+idCategory+" for name "+category+" , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category1, HttpStatus.OK);
    }

    //Update Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/category/id/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSingleCategory(@RequestParam("id") long idCategory, @RequestBody Category category) {
        logger.info("Showing Category with id {} ...", idCategory);

        Category beforeUpdateCategory = categoryService.findByIdCategory(idCategory);
        if (beforeUpdateCategory == null) {
            logger.error("Unable to show Category, because category id {} is not found", idCategory);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Category "+idCategory+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            categoryService.updateDataCategory(idCategory,category);
            Category newUpdateCategory = categoryService.findByIdCategory(idCategory);
            return new ResponseEntity<>(newUpdateCategory, HttpStatus.OK);
        }
    }

    //Delete All Data-------------------------------------------------------------------------
    @RequestMapping(value = "/category/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteCategory() {
        logger.info("Delete All Category ... ");
        categoryService.deleteAllCategory();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/category/id/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProductById(@RequestParam("id") long idCategory) {
        logger.info("Delete Category with id {} ...", idCategory);

        Category findingId = categoryService.findByIdCategory(idCategory);
        if (findingId == null) {
            logger.error("Unable to deleting that Category, because category id {} is not found", idCategory);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Category "+idCategory+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            categoryService.deleteByIdCategory(idCategory);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //Delete Data By Name---------------------------------------------------------------------
    @RequestMapping(value = "/category/name/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProductByName(@RequestParam("name") String category) {
        logger.info("Delete Category with name {} ...", category);

        Category findingName = categoryService.findByNameCategory(category);
        if (findingName == null) {
            logger.error("Unable to deleting that Category, because category name {} is not found", category);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Category "+category+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            categoryService.deleteByNameCategory(category);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
