package com.example.demo.repository;

import com.example.demo.model.carts.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Cart> getListCartProductByUserId(Integer userId) {

        List<Cart> res =  new ArrayList<Cart>();

        try {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("userId", userId);

            //sql query
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT id, user_id, product_id, quantity, created_at, updated_at ");
            sql.append(" FROM public.cart WHERE user_id=:userId ");

            return namedParameterJdbcTemplate.query(sql.toString(), sqlParameterSource, BeanPropertyRowMapper.newInstance(Cart.class));

        }catch (Exception e) {

            //res = null;
        }

        return res;

    }


    public List<Cart> getListCartProductByUserIdAndProductId(Integer productId, Integer userId) {

        List<Cart> res =  new ArrayList<Cart>();

        try {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("userId", userId);
            sqlParameterSource.addValue("productId", productId);

            //sql query
            StringBuilder sql = new StringBuilder();
            sql.append(" SELECT id, user_id, product_id, quantity, created_at, updated_at ");
            sql.append(" FROM public.cart WHERE user_id=:userId AND product_id=:productId ");

            return namedParameterJdbcTemplate.query(sql.toString(), sqlParameterSource, BeanPropertyRowMapper.newInstance(Cart.class));

        }catch (Exception e) {

            //res = null;
        }

        return res;

    }

    public Integer addProductToCart(Integer productId, Integer qty, Integer userId) {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("productId", productId);
            sqlParameterSource.addValue("qty", qty);
            sqlParameterSource.addValue("userId", userId);

            //sql query
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO public.cart ");
            sql.append(" (user_id, product_id, quantity, created_at) ");
            sql.append(" VALUES(:userId, :productId, :qty, CURRENT_TIMESTAMP) ");

            return namedParameterJdbcTemplate.update(sql.toString(), sqlParameterSource);


    }

    public Integer updateProductFromCart(Integer qty, Integer userId,Long cartId) {

        //Create parameter source
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("cartId", cartId);
        sqlParameterSource.addValue("qty", qty);
        sqlParameterSource.addValue("userId", userId);

        //sql query
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE public.cart ");
        sql.append(" SET quantity=:qty , updated_at=CURRENT_TIMESTAMP ");
        sql.append(" WHERE user_id=:userId AND id=:cartId ");

        return namedParameterJdbcTemplate.update(sql.toString(), sqlParameterSource);

    }

    public Integer deleteProductFromCart(Integer userId,Integer cartId) {

        //Create parameter source
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("cartId", cartId);
        sqlParameterSource.addValue("userId", userId);

        return namedParameterJdbcTemplate.update(" DELETE FROM public.cart WHERE user_id=:userId AND id=:cartId "
                , sqlParameterSource);

    }

}
