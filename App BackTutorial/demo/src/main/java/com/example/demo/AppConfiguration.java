package com.example.demo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration(proxyBeanMethods = false)
public class AppConfiguration {

    @Bean(name="testdb")
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .driverClassName("org.h2.Driver")
                .url("jdbc:h2:mem:testdb")
                .username("juanantonio")
                .password("1234")
                .build();
    }

    @Bean(name="jdbcTemplateMethod")
    public JdbcTemplate jdbcTemplate(final @Qualifier("testdb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name="jdbcTemplate")
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
