package com.project.simplecrud.controller;

import com.project.simplecrud.model.Product;
import com.project.simplecrud.service.CategoryService;
import com.project.simplecrud.service.ProductService;
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
public class ProductController {

    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService; //Places to all manipulate data, CRUD, and work with data

    @Autowired
    CategoryService categoryService; //Places to all manipulate data, CRUD, and work with data

    //Create Product Data---------------------------------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.POST)
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        logger.info("Creating Product : {}", product);

        if (categoryService.findByIdCategory(product.getIdCategory()) == null) {
            logger.error("Unable to create Product, because category with id {} is not found", product.getIdCategory());
            return new ResponseEntity<>(new CustomErrorType("Unable to Insert product with id " + product.getIdCategory() + " , because not found"), HttpStatus.CONFLICT);
        } else {
            if (productService.isProductExist(product)) {
                logger.error("Unable to create Product, Product with name {} already exist", product.getProduct());
                return new ResponseEntity<>(new CustomErrorType("Unable to create. Product with name " + product.getProduct() + " already exist."), HttpStatus.CONFLICT);
            } else {
                productService.saveProduct(product);
                Product productSave = productService.findByIdProduct(product.getIdProduct());
                return new ResponseEntity<>(productSave, HttpStatus.CREATED);
            }
        }
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    public ResponseEntity<List<Product>> showProduct() {
        logger.info("Showing Product ... ");
        List<Product> products = productService.findAllProduct();

        if (products.isEmpty()) {
            logger.error("Unable to show Product, because empty on Database");
            return new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    //Show Data By Name-----------------------------------------------------------------------
    @RequestMapping(value = "/product/name/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProduct(@RequestParam("name") String product) {
        logger.info("Showing Product with name {} ...", product);

        Product product1 = productService.findByNameProduct(product);
        if (product1 == null) {
            logger.error("Unable to show Product, because product name {} is not found", product);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + product + " , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    //Show Data By Id-------------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleCategory(@RequestParam("id") String idProduct) {
        logger.info("Showing Product with id {} ...", idProduct);

        Product product1 = productService.findByIdProduct(idProduct);
        if (product1 == null) {
            logger.error("Unable to show Product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + idProduct + " , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    //Show Data By Id and Name----------------------------------------------------------------
    @RequestMapping(value = "/product/detail/", method = RequestMethod.GET)
    public ResponseEntity<?> showSingleProduct(@RequestParam("id") String idProduct, @RequestParam("name") String product) {
        logger.info("Showing Product with id {} name {} ...", idProduct, product);

        Product product1 = productService.findByIdAndNameProduct(idProduct, product);
        if (product1 == null) {
            logger.error("Unable to show Product, because product id {} and name {} is not found", idProduct, product);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product " + idProduct + " for name " + product + " , because not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product1, HttpStatus.OK);
    }

    //Update Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSingleProduct(@RequestParam("id") String idProduct, @RequestBody Product product) {
        logger.info("Showing Product with id {} ...", idProduct);

        Product beforeUpdateProduct = productService.findByIdProduct(idProduct);
        if (beforeUpdateProduct == null) {
            logger.error("Unable to show Product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to show Product "+idProduct+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            productService.updateDataProduct(idProduct, product);
            Product newUpdateProduct = productService.findByIdProduct(idProduct);
            return new ResponseEntity<>(newUpdateProduct, HttpStatus.OK);
        }
    }

    //Delete All Data-------------------------------------------------------------------------
    @RequestMapping(value = "/product/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct() {
        logger.info("Delete All Product ... ");
        productService.deleteAllProduct();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Delete Data By Id-----------------------------------------------------------------------
    @RequestMapping(value = "/product/id/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProductById(@RequestParam("id") String idProduct) {
        logger.info("Delete Product with id {} ...", idProduct);

        Product findingId = productService.findByIdProduct(idProduct);
        if (findingId == null) {
            logger.error("Unable to deleting that Product, because product id {} is not found", idProduct);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Product "+idProduct+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            productService.deleteByIdProduct(idProduct);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    //Delete Data By Name---------------------------------------------------------------------
    @RequestMapping(value = "/product/name/", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteSingleProductByName(@RequestParam("name") String product) {
        logger.info("Delete Product with name {} ...", product);

        Product findingName = productService.findByNameProduct(product);
        if (findingName == null) {
            logger.error("Unable to deleting that Product, because product name {} is not found", product);
            return new ResponseEntity<>(new CustomErrorType("Unable to deleting that Product "+product+" , because not found"), HttpStatus.NOT_FOUND);
        } else {
            productService.deleteByNameProduct(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
