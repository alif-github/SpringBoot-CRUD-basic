package com.project.simplecrud.service;

import com.project.simplecrud.model.Cart;
import com.project.simplecrud.model.Category;
import com.project.simplecrud.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Override
    public void saveCart(Cart cart) {
        synchronized (this) {
            cartRepository.saveCart(cart);
        }
    }

    @Override
    public List<Cart> findAllCart() {
        List<Cart> carts = cartRepository.findAllCart();
        return carts;
    }

    @Override
    public List<Cart> findAllCartSave() {
        return null;
    }

    @Override
    public List<Cart> findAllCartwPaggination(int page, int limit) {
        List<Cart> carts = cartRepository.findAllCartwPaggination(page, limit);
        return carts;
    }

    @Override
    public Cart findByIdCart(String idCart) {
        Cart obj;
        try {
            obj = cartRepository.findByIdCart(idCart);
        } catch (Exception e) {
            obj = null;
        }
        return obj;
    }

    @Override
    public Category findByidCustomer(int idCustomer) {
        return null;
    }

    @Override
    public void updateCartDetail(Cart cart) {
        synchronized (this) {
            cartRepository.updateCartDetail(cart);
        }
    }

    @Override
    public void updateCartStatus(Cart cart) {
        synchronized (this) {
            cartRepository.updateCartStatus(cart);
        }
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

    @Override
    public boolean isCartExist(Cart cart) {
        return false;
    }
}
