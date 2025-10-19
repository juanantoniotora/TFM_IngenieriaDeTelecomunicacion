package com.example.demo.repository;

import com.example.demo.entity.UsuariosEntity;
import com.example.demo.model.Usuario;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class UsuariosRepositoryJDBCImpl {

    final Logger logger = LoggerFactory.getLogger(UsuariosRepositoryJDBCImpl.class);

    private final String findAllUsersQuery;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public UsuariosRepositoryJDBCImpl(@Qualifier("jdbcTemplate") final NamedParameterJdbcTemplate jdbcTemplate,
                                      @Value("${sql.findAllUsers}") final String findAllUsersQuery) {
        this.findAllUsersQuery = findAllUsersQuery;
        this.jdbcTemplate = jdbcTemplate;
    }

    public UsuariosEntity findUser(final Integer userId) {
        this.logger.info("INICIO [UsuariosRepositoryJDBCImpl.findUser] Encontrado usuario por id.");

        final Map<String, Object> params = new HashMap<>();
        params.put("id", userId);
        UsuariosEntity result = this.jdbcTemplate.query(findAllUsersQuery, params, new UserEntityRowMapper()).get(0);

        this.logger.info("FIN [UsuariosRepositoryJDBCImpl.findUser] Encontrado usuario por id.");
        return result;

    }


}
