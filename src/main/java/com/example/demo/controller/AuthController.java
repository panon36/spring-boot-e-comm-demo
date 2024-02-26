package com.example.demo.controller;

import com.example.demo.model.CommonResponse;
import com.example.demo.model.auth.UserAuthen;
import com.example.demo.model.users.Users;
import com.example.demo.service.AuthService;
import com.example.demo.service.UsersService;
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

@RestController
@RequestMapping("${base.url}/auth")
@Slf4j
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    UsersService usersService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody HashMap bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();
        String username = (String) bodyRequest.get("username");
        String pwd = (String) bodyRequest.get("pwd");

        try {

            UserAuthen userAuthenResult =  authService.getUsersAuth(username,pwd);

            if(userAuthenResult != null) {

                commonResponse.setResultData(userAuthenResult);
                commonResponse.setStatus(true);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

            }else{

                commonResponse.setStatus(false);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.FIND_NOT_FOUND_USER.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.FIND_NOT_FOUND_USER.getCode());

            }

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc("Login process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());

            e.printStackTrace();
            log.error("AuthController => Login Exception : {}" , e.getMessage());

        }finally {

            log.info("AuthController => Login => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserAuthen bodyRequest) throws Exception {

        Long startTime = System.currentTimeMillis();
        CommonResponse commonResponse = new CommonResponse();

        try {
            //check object UserAuthen null
            if(bodyRequest != null) {

                //validate not empty  username,password,email,firstname,lastname
                if(bodyRequest.getUsername() != null &&
                        bodyRequest.getPwd() != null &&
                        bodyRequest.getEmail() != null &&
                        bodyRequest.getFirstName() != null &&
                        bodyRequest.getLastName() != null){

                    //validate username already
                    Users users = usersService.getUsersByUsername(bodyRequest.getUsername().trim());

                    if(users == null){

                        //add new user
                        authService.addUserRegister(bodyRequest);

                        commonResponse.setStatus(true);
                        commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getDesc());
                        commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.SUCCESS.getCode());

                    }else{

                        commonResponse.setStatus(false);
                        commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.USER_ALREADY.getDesc());
                        commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.USER_ALREADY.getCode());

                    }

                }else{

                    commonResponse.setStatus(false);
                    commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.INVALID_PARAM.getDesc(" username,password,email,firstname,lastname"));
                    commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.INVALID_PARAM.getCode());

                }

            }else{

                commonResponse.setStatus(false);
                commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.INVALID_PARAM.getDesc());
                commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.INVALID_PARAM.getCode());

            }

            return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.OK);

        } catch (Exception e) {

            commonResponse.setStatus(false);
            commonResponse.setResultMessage(DemoConstant.RESPONSE_MESSAGE.ERROR.getDesc("register process failed."));
            commonResponse.setStatusCode(DemoConstant.RESPONSE_MESSAGE.ERROR.getCode());
            log.error("AuthController => Register Exception : {}" , e.getMessage());

        }finally {

            log.info("AuthController => Register => Process Elapsed time in seconds: {}" , ExecutionTime.elapsedProcessTimeSeconds(startTime));

        }

        return new ResponseEntity<CommonResponse>(commonResponse, HttpStatus.INTERNAL_SERVER_ERROR);

    }





}
