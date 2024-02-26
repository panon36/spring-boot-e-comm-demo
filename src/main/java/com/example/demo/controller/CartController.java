package com.example.demo.controller;

import com.example.demo.model.CommonResponse;
import com.example.demo.model.carts.Cart;
import com.example.demo.service.CartService;
import com.example.demo.util.DemoConstant;
import com.example.demo.util.ExecutionTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("${base.url}/cart")
@Slf4j
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/getListCartProductByUserId")
    public ResponseEntity<?> getListCartProductByUserId(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        Integer userId = (Integer) bodyRequest.get("userId");

        try {

            List<Cart> listProductResult =  cartService.getListCartProductByUserId(userId);

            commonResponse.setResultData(listProductResult);
            commonResponse.setStatus(true);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());
            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc(" get Cart process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());
            e.printStackTrace();
            log.error("CartController => getListCartProductByUserId Exception : {}" , e.getMessage());

        }finally {

            log.info("CartController => getListCartProductByUserId => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PostMapping("/addProductToCart")
    public ResponseEntity<?> addProductToCart(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        Integer productId = (Integer) bodyRequest.get("productId");
        Integer qty = (Integer) bodyRequest.get("qty");
        Integer userId = (Integer) bodyRequest.get("userId");
        try {

            Integer addProductToCartResult =  cartService.addProductToCart(productId,qty,userId);

            if(addProductToCartResult > 0){

                commonResponse.setResultData(addProductToCartResult);
                commonResponse.setStatus(true);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            }else{

                commonResponse.setResultData(addProductToCartResult);
                commonResponse.setStatus(false);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            }

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc(" add Product To Cart process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());
            e.printStackTrace();
            log.error("CartController => addProductToCart Exception : {}" , e.getMessage());

        }finally {

            log.info("CartController => addProductToCart => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PostMapping("/updateProductFromCart")
    public ResponseEntity<?> updateProductFromCart(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        Integer productId = (Integer) bodyRequest.get("productId");
        Integer qty = (Integer) bodyRequest.get("qty");
        Integer userId = (Integer) bodyRequest.get("userId");

        try {

            Integer addProductToCartResult =  cartService.addProductToCart(productId,qty,userId);

            if(addProductToCartResult > 0){

                commonResponse.setResultData(addProductToCartResult);
                commonResponse.setStatus(true);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            }else{

                commonResponse.setResultData(addProductToCartResult);
                commonResponse.setStatus(false);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            }

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc(" add update Product From Cart process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());
            e.printStackTrace();
            log.error("CartController => updateProductFromCart Exception : {}" , e.getMessage());

        }finally {

            log.info("CartController => updateProductFromCart => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PostMapping("/deleteProductFromCart")
    public ResponseEntity<?> deleteProductFromCart(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        Integer cartId = (Integer) bodyRequest.get("cartId");
        Integer userId = (Integer) bodyRequest.get("userId");

        try {

            Integer deleteProductFromCartResult =  cartService.deleteProductFromCart(cartId,userId);

            if(deleteProductFromCartResult > 0){

                commonResponse.setResultData(deleteProductFromCartResult);
                commonResponse.setStatus(true);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            }else{

                commonResponse.setResultData(deleteProductFromCartResult);
                commonResponse.setStatus(false);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            }

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc(" add delete Product From Cart process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());
            e.printStackTrace();
            log.error("CartController => deleteProductFromCart Exception : {}" , e.getMessage());

        }finally {

            log.info("CartController => deleteProductFromCart => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


}
