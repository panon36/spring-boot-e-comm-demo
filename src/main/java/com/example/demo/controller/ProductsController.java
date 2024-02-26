package com.example.demo.controller;

import com.example.demo.model.CommonResponse;
import com.example.demo.model.products.Product;
import com.example.demo.service.ProductService;
import com.example.demo.util.DemoConstant;
import com.example.demo.util.ExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${base.url}/product")
@Slf4j
public class ProductsController {

    @Autowired
    ProductService productService;

    @GetMapping("/getAllProductsList")
    public ResponseEntity<?> getAllProductsList() throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();

        try {

            List<Product> listProductResult =  productService.getListAllProducts();

                commonResponse.setResultData(listProductResult);
                commonResponse.setStatus(true);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc("get all product process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            e.printStackTrace();
            log.error("ProductsController => getAllProductsList Exception : {}" , e.getMessage());

        }finally {

            log.info("ProductsController => getAllProductsList => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PostMapping("/getProductDetail")
    public ResponseEntity<?> getProductDetail(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        Integer productId = (Integer) bodyRequest.get("productId");
        try {

            Product productDetailResult =  productService.getProductDetail(productId);

            commonResponse.setResultData(productDetailResult);
            commonResponse.setStatus(true);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc("get product detail process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            e.printStackTrace();
            log.error("ProductsController => getProductDetail Exception : {}" , e.getMessage());

        }finally {

            log.info("ProductsController => getProductDetail => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
