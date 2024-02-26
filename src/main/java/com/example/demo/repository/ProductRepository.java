package com.example.demo.repository;

import com.example.demo.model.products.Product;
import com.example.demo.model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Product> getListAllProducts() {

        List<Product> res =  new ArrayList<Product>();

        try {

            //sql query
            String sql = " SELECT id, name, description, category_id, brand_id, price, stock, image_url, created_at, updated_at, unit, sale_price " +
                    " FROM public.products ";

            return namedParameterJdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Product.class));

        } catch (Exception e) {

            //res = null;

        }

        return res;

    }


    public Product getProductDetail(Integer productId) {

        Product res =  new Product();

        try {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("productId", productId);

            //sql query
            String sql = " SELECT id, name, description, category_id, brand_id, price, stock, image_url, created_at, updated_at, unit, sale_price " +
                    " FROM public.products WHERE id = :productId ";

            return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource,
                    BeanPropertyRowMapper.newInstance(Product.class));


        } catch (Exception e) {

            res = null;

        }

        return res;

    }

}
