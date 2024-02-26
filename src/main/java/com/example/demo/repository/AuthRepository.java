package com.example.demo.repository;

import com.example.demo.model.auth.UserAuthen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //jdbc
    public UserAuthen getUsersAuth(String userName, String pwd) {

        UserAuthen res = new UserAuthen();

        try {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("userName", userName);
            sqlParameterSource.addValue("pwd", pwd);
            sqlParameterSource.addValue("userStatus", "A"); //status A = "Active"

            //sql query
            String sql = " SELECT id, username, email, password as pwd, first_name, last_name " +
                    " FROM public.users WHERE username=:userName AND password=:pwd AND user_status=:userStatus ";

            return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource,
                    BeanPropertyRowMapper.newInstance(UserAuthen.class));

        } catch (Exception e) {

            res = null;

        }

        return res;

    }


    public Integer  addUserRegister(UserAuthen userAuthen) {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("userName", userAuthen.getUsername());
            sqlParameterSource.addValue("email", userAuthen.getEmail());
            sqlParameterSource.addValue("password", userAuthen.getPwd());
            sqlParameterSource.addValue("firstName", userAuthen.getFirstName());
            sqlParameterSource.addValue("lastName", userAuthen.getLastName());
            sqlParameterSource.addValue("mobile", userAuthen.getMobileNumber());
            sqlParameterSource.addValue("userStatus", "A");  //status A = "Active"

            //sql query
            StringBuilder sql = new StringBuilder();
            sql.append(" INSERT INTO public.users ");
            sql.append(" (username, email, password, first_name, last_name, user_status, mobile, created_at, updated_at) ");
            sql.append(" VALUES(:userName, :email, :password,:firstName, :lastName, :mobile, :userStatus, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP); ");

            // Create key holder to store generated keys
            KeyHolder keyHolder = new GeneratedKeyHolder();
            namedParameterJdbcTemplate.update(sql.toString(), sqlParameterSource, keyHolder);

            // Retrieve the generated key
            Number generatedId = keyHolder.getKey();

            // Assuming the generated key is an integer
            if(generatedId != null) {
                return generatedId.intValue();
            } else {
                throw new RuntimeException("Failed to retrieve auto-generated ID after insert");
            }


        }


    }



