package com.example.demo.repository;

import com.example.demo.model.auth.UserAuthen;
import com.example.demo.model.users.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public Users getUsersByUsername(String userName) {

        Users res = new Users();

        try {

            //Create parameter source
            MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
            sqlParameterSource.addValue("userName", userName);

            //sql query
            String sql = " SELECT id, username, email, password as pwd, first_name, last_name " +
                    " FROM public.users WHERE username=:userName ";

            return namedParameterJdbcTemplate.queryForObject(sql, sqlParameterSource,
                    BeanPropertyRowMapper.newInstance(Users.class));

        } catch (Exception e) {

            res = null;

        }

        return res;

    }

}
