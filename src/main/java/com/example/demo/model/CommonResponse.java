package com.example.demo.model;


import lombok.Data;

@Data
public class CommonResponse {

    private boolean status;
    private Object resultData;
    private Object resultMessage;
    private String statusCode;


}
