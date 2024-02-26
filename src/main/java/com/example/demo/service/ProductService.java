package com.example.demo.service;

import com.example.demo.model.products.Product;
import com.example.demo.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    ProductRepository getProductRepository;

    public List<Product> getListAllProducts() {

        List<Product> resp = new ArrayList<Product>();

        try {

            List<Product> result  = getProductRepository.getListAllProducts();

            if(!result.isEmpty()){

                resp = result;

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("ProductService => getListAllProducts failed {}",e.getMessage());

        }

        return resp;

    }


    public Product getProductDetail(Integer productId) {

        Product resp = null;

        try {

            Product result  = getProductRepository.getProductDetail(productId);

            if(result != null) {

                resp = new Product();
                resp = result;

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("ProductService => getProductDetail failed {}",e.getMessage());

        }

        return resp;

    }

}
