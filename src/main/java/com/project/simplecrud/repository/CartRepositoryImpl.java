package com.project.simplecrud.repository;

import com.project.simplecrud.model.Cart;
import com.project.simplecrud.model.Category;
import com.project.simplecrud.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository("cartRepository")
public class CartRepositoryImpl implements CartRepository {

    @Autowired
    JdbcTemplate databases;

    @Override
    public void saveCart(Cart cart) {
        String uuidRandom = UUID.randomUUID().toString();
        cart.setIdCart(uuidRandom);
        cart.setTransactionDate(new Date());
        String sql = "INSERT INTO cart(idCart,transactionDate,idCustomer,status) VALUES(?,?,?,?)";
        databases.update(sql,
                cart.getIdCart(),
                cart.getTransactionDate(),
                cart.getIdCustomer(),
                cart.isStatus()
        );

        List<Product> products = cart.getProducts();
        for (int i = 0; i < products.size(); i++) {
            String uuidRandom1 = UUID.randomUUID().toString();
            String sql1 = "INSERT INTO cartdetail(idCartDetail,idCart,idProduct,qty) VALUES(?,?,?,?)";
            databases.update(sql1,
                    uuidRandom1,
                    cart.getIdCart(),
                    products.get(i).getIdProduct(),
                    0);

        }

    }

    @Override
    public List<Cart> findAllCart() {
        String sql = "SELECT * FROM cart";
        List<Cart> carts;
        carts = databases.query(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        )
        );
        for (Cart cart : carts) {
            String sql1 = "SELECT * FROM cartdetail c, product p WHERE c.idProduct = p.idProduct AND idCart=?";
            cart.setProducts(databases.query(sql1,
                    preparedStatement -> preparedStatement.setString(1,cart.getIdCart()),
                    (rs, rowNum) ->
                            new Product(
                                    rs.getString("idProduct"),
                                    rs.getString("product"),
                                    rs.getInt("idCategory"),
                                    rs.getDouble("price")
                            )
            ));
        }
        return carts;
    }

    @Override
    public List<Cart> findAllCartSave() {
        return null;
    }

    @Override
    public List<Cart> findAllCartwPaggination(int page, int limit) {
        int numPages;
        numPages = databases.query("SELECT COUNT(*) as count FROM cart",
                (rs, rowNum) ->
                        rs.getInt("count")).get(0);

        //validatePage
        if (page < 1) page = 1;
        if (page > numPages) page = numPages;

        int start = (page - 1) * limit;

        String sql = "SELECT * FROM cart LIMIT "+start+","+limit+"";
        List<Cart> carts;
        carts = databases.query(sql,
                (rs, rowNum) ->
                        new Cart(
                                rs.getString("idCart"),
                                rs.getDate("transactionDate"),
                                rs.getInt("idCustomer"),
                                rs.getBoolean("status"),
                                null
                        )
        );
        for (Cart cart : carts) {
            String sql1 = "SELECT * FROM cartdetail c, product p WHERE c.idProduct = p.idProduct AND idCart=?";
            cart.setProducts(databases.query(sql1,
                    preparedStatement -> preparedStatement.setString(1,cart.getIdCart()),
                    (rs, rowNum) ->
                            new Product(
                                    rs.getString("idCart"),
                                    rs.getString("product"),
                                    rs.getInt("idCategory"),
                                    rs.getDouble("price")
                            )
            ));
        }
        return carts;
    }

    @Override
    public Cart findByIdCart(String idCart) {
        Cart cart;
        String sql = "SELECT * FROM cart WHERE idCart=?";
        cart = databases.query(sql,
                preparedStatement -> preparedStatement.setString(1,idCart),
                (rs, rowNum) ->
                new Cart(
                        rs.getString("idCart"),
                        rs.getDate("transactionDate"),
                        rs.getInt("idCustomer"),
                        rs.getBoolean("status"),
                        null
                )).get(0);
        String sql1 = "SELECT * FROM cartdetail c, product p WHERE c.idProduct = p.idProduct AND c.idCart=?";
        cart.setProducts(databases.query(sql1,
                preparedStatement -> preparedStatement.setString(1,cart.getIdCart()),
                (rs, rowNum) ->
                new Product(
                        rs.getString("idProduct"),
                        rs.getString("product"),
                        rs.getInt("idCategory"),
                        rs.getDouble("price")
                )));
        return cart;
    }

    @Override
    public Category findByidCustomer(int idCustomer) {
        return null;
    }

    @Override
    public void updateCartDetail(Cart cart) {
        updateCartStatus(cart);

        String sql = "DELETE FROM cartdetail WHERE idCart=?";
        databases.update(sql,cart.getIdCart());

        List<Product> products = cart.getProducts();
        String uuidRandom = UUID.randomUUID().toString();
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            String sql1 = "INSERT INTO cartdetail (idCartDetail, idCart, idProduct, qty) VALUES (?,?,?,?)";
            databases.update(sql1,uuidRandom,cart.getIdCart(),products.get(i).getIdProduct(),0);
        }
    }

    @Override
    public void updateCartStatus(Cart cart) {
        String sqlUpdateStatus = "UPDATE cart\n" +
                "SET status = ? \n" +
                "WHERE idCart = ?;";
        databases.update(sqlUpdateStatus, cart.isStatus(), cart.getIdCart());
    }

    @Override
    public void deleteAllCart() {

    }

    @Override
    public void deleteByIdCart(String idCart) {

    }

    @Override
    public void deleteByidCustomer(int idCustomer) {

    }
}
