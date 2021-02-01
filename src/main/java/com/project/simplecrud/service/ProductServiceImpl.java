package com.project.simplecrud.service;

import com.project.simplecrud.model.Product;
import com.project.simplecrud.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public void saveProduct(Product product) {
        synchronized (this) { //Critical section synchronized
            productRepository.saveProduct(product);
        }
    }

    @Override
    public List<Product> findAllProduct() {
        List<Product> products = productRepository.findAllProduct();
        return products;
    }

    @Override
    public List<Product> findAllProductSave() {
        List<Product> products = productRepository.findAllProductSave();
        return products;
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        Product product1;
        try {
            product1 = productRepository.findByIdProduct(idProduct);
        } catch (Exception e) {
            product1 = null;
        }
        return product1;
    }

    @Override
    public Product findByNameProduct(String product) {
        Product product1;
        try {
            product1 = (Product) productRepository.findByNameProduct(product).get(0);
        } catch (Exception e) {
            product1 = null;
        }
        return product1;
    }

    @Override
    public Product findByIdAndNameProduct(String idProduct, String product) {
        Product product1;
        try {
            product1 = productRepository.findByIdAndNameProduct(idProduct, product);
        } catch (Exception e) {
            product1 = null;
        }
        return product1;
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {
        synchronized (this) {
            productRepository.updateDataProduct(idProduct,product);
        }
    }

    @Override
    public void deleteAllProduct() {
        productRepository.deleteAllProduct();
    }

    @Override
    public void deleteByIdProduct(String idProduct) {
        synchronized (this) {
            productRepository.deleteByIdProduct(idProduct);
        }
    }

    @Override
    public void deleteByNameProduct(String product) {
        synchronized (this) {
            productRepository.deleteByNameProduct(product);
        }
    }

    @Override
    public boolean isProductExist(Product product) {
        return productRepository.findByNameProduct(product.getProduct()).size() != 0;
    }
}
