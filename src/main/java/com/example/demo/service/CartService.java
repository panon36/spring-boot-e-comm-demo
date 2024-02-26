package com.example.demo.service;

import com.example.demo.model.auth.UserAuthen;
import com.example.demo.model.carts.Cart;
import com.example.demo.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CartService {

    @Autowired
    CartRepository cartRepository;

    public List<Cart> getListCartProductByUserId(Integer userId) {

        List<Cart> resp = new ArrayList<Cart>();

        try {

            List<Cart> result  = cartRepository.getListCartProductByUserId(userId);

            if(!result.isEmpty()){

                resp = result;

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("CartService => getListCartProductByUserId failed {}",e.getMessage());

        }

        return resp;

    }


    public Integer addProductToCart(Integer productId, Integer qty, Integer userId) {

        Integer resp = 0;

        try {

            List<Cart> cartListResult = cartRepository.getListCartProductByUserIdAndProductId(productId,userId);

            Integer result  = 0;

            if(!cartListResult.isEmpty()) {

                Long cartId  = cartListResult.get(0).getId();
                result = cartRepository.updateProductFromCart(qty,userId,cartId);

            }else{

                result = cartRepository.addProductToCart(productId,qty,userId);
            }

            if(result != 0){
                resp = result;
            }

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
            log.error("CartService => addProductToCart failed {}",e.getMessage());

        }

        return resp;

    }


    public Integer deleteProductFromCart(Integer cartId, Integer userId) {

        Integer resp = 0;

        try {

            resp = cartRepository.deleteProductFromCart(userId,cartId);

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
            log.error("CartService => addProductToCart failed {}",e.getMessage());

        }

        return resp;

    }



}
