package com.project.simplecrud.controller;

import com.project.simplecrud.model.Cart;
import com.project.simplecrud.service.CartService;
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
public class CartController {

    public static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartService cartService; //Places to all manipulate data, CRUD, and work with data

    //Create Product Data---------------------------------------------------------------------
    @RequestMapping(value = "/cart/", method = RequestMethod.POST)
    public ResponseEntity<?> createCart(@RequestBody Cart cart) {
        logger.info("Creating Cart : {}", cart);
        cartService.saveCart(cart);
        Cart cartCurrent = cartService.findByIdCart(cart.getIdCart());
        return new ResponseEntity<>(cartCurrent, HttpStatus.CREATED);
    }

    //Show All Data---------------------------------------------------------------------------
    @RequestMapping(value = "/cart/", method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> showCart() {
        List<Cart> carts = cartService.findAllCart();
        if (carts.isEmpty()) {
            return new ResponseEntity<>(carts, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }
    }

    //Show All Data with Pagging--------------------------------------------------------------
    @RequestMapping(value = "/cart/pagging/", method = RequestMethod.GET)
    public ResponseEntity<List<Cart>> showCartWPagging(@RequestParam("page") int page, @RequestParam("limit") int limit) {
        List<Cart> carts = cartService.findAllCartwPaggination(page, limit);
        if (carts.isEmpty()) {
            return new ResponseEntity<>(carts, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(carts, HttpStatus.OK);
        }
    }

    //Show Data By ID-------------------------------------------------------------------------
    @RequestMapping(value = "/cart/{idCart}", method = RequestMethod.GET)
    public ResponseEntity<?> showCartById(@PathVariable("idCart") String idCart) {
        logger.info("Fetching Cart with idCart {}", idCart);
        Cart cart = cartService.findByIdCart(idCart);
        if (cart == null) {
            logger.error("Cart with idCart {} not found.", idCart);
            return new ResponseEntity<>(new CustomErrorType("Cart with idCart " + idCart + " not found."), HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cart, HttpStatus.OK);
        }
    }

    //Update Data-----------------------------------------------------------------------------
    //Update Cart Detail Data-----------------------------------------------------------------
    @RequestMapping(value = "/cart/", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCartDetail(@RequestBody Cart cart) {
        logger.info("Updating Cart with id {}", cart.getIdCart());

        Cart currentCart = cartService.findByIdCart(cart.getIdCart());

        if (currentCart == null) {
            logger.error("Unable to update. Cart with id {} not found.", cart.getIdCart());
            return new ResponseEntity<>(new CustomErrorType("Unable to update. Cart with id " + cart.getIdCart() + " not found."),
                    HttpStatus.NOT_FOUND);
        } else {
            cartService.updateCartDetail(cart);
            Cart newUpdate = cartService.findByIdCart(cart.getIdCart());
            return new ResponseEntity<>(newUpdate, HttpStatus.CREATED);
        }
    }

    //Update Cart Detail Data-----------------------------------------------------------------
}
