package com.project.simplecrud.repository;

import com.project.simplecrud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("productRepository")
public class ProductRepositoryImpl implements ProductRepository {

    @Autowired
    JdbcTemplate databases;

    @Override
    public void saveProduct(Product product) {
        String uuidRandom = UUID.randomUUID().toString();
        product.setIdProduct(uuidRandom);
        String sql = "INSERT INTO product(idProduct,product,idCategory,price) VALUES(?,?,?,?)";
        databases.update(sql,
                product.getIdProduct(),
                product.getProduct(),
                product.getIdCategory(),
                product.getPrice());
    }

    @Override
    public List<Product> findAllProduct() {
        String sql = "SELECT * FROM product";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getLong("idCategory"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public List<Product> findAllProductSave() {
        String sql = "SELECT * FROM product ORDER BY idProduct DESC LIMIT 1";
        return databases.query(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getLong("idCategory"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public Product findByIdProduct(String idProduct) {
        String sql = "SELECT * FROM product WHERE idProduct ='"+idProduct+"'";
        return databases.queryForObject(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getLong("idCategory"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public List<Product> findByNameProduct(String product) {
        String sql = "SELECT * FROM product WHERE product LIKE ?";
        return databases.query(sql,
                new Object[]{"%"+product+"%"},
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getLong("idCategory"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public Product findByIdAndNameProduct(String idProduct, String product) {
        String sql = "SELECT * FROM product WHERE idProduct ='"+idProduct+"' AND product ='"+product+"'";
        return databases.queryForObject(sql,
                (rs, rowNum) ->
                        new Product(
                                rs.getString("idProduct"),
                                rs.getString("product"),
                                rs.getLong("idCategory"),
                                rs.getDouble("price")
                        ));
    }

    @Override
    public void updateDataProduct(String idProduct, Product product) {
        String sql = "UPDATE product\n" +
                "SET product = ?, idCategory = ?, price = ?\n" +
                "WHERE idProduct = ?;";
        databases.update(sql, product.getProduct(), product.getIdCategory(),
                product.getPrice(), idProduct);
    }

    @Override
    public void deleteAllProduct() {
        String sql = "DELETE FROM product";
        databases.execute(sql);
    }

    @Override
    public void deleteByIdProduct(String idProduct) {
        String sql = "DELETE FROM product WHERE idProduct='"+idProduct+"'";
        databases.execute(sql);
    }

    @Override
    public void deleteByNameProduct(String product) {
        String sql = "DELETE FROM product WHERE product='"+product+"'";
        databases.execute(sql);
    }
}
