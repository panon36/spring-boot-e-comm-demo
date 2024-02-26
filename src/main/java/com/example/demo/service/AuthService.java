package com.example.demo.service;

import com.example.demo.model.auth.UserAuthen;
import com.example.demo.repository.AuthRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AuthService {

    @Autowired
    AuthRepository authRepository;

    public UserAuthen getUsersAuth(String userName,String pwd) {

        UserAuthen resp = null;

        try {

            UserAuthen result  = authRepository.getUsersAuth(userName,pwd);

            if(result != null){

                resp = new UserAuthen();
                resp = result;

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("AuthService => getUsersAuth failed {}",e.getMessage());

        }

        return resp;

    }


    public Integer addUserRegister(UserAuthen userAuthen) {

        Integer resp = 0;

        try {

            Integer result  = authRepository.addUserRegister(userAuthen);

            if(result != 0){
                resp = result;
            }

        } catch (Exception e) {

            // TODO: handle exception
            e.printStackTrace();
            log.error("AuthService => addUserRegister failed {}",e.getMessage());

        }

        return resp;

    }



}
