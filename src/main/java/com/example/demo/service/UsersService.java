package com.example.demo.service;

import com.example.demo.model.users.Users;
import com.example.demo.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UsersService {

    @Autowired
    UsersRepository usersRepository;

    public Users getUsersByUsername(String userName) {

        Users resp = null;

        try {

            Users result  = usersRepository.getUsersByUsername(userName);

            if(result != null){

                resp = new Users();
                resp = result;

            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            log.error("UsersService => getUsersByUsername failed {}",e.getMessage());

        }

        return resp;

    }


}
