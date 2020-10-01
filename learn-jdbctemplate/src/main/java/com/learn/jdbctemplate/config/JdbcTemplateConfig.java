package com.learn.jdbctemplate.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * @author Hai
 * @date 2020/2/11 - 17:21
 */
@Configuration
public class JdbcTemplateConfig {
  @Bean
  JdbcTemplate jdbcTemplateOne(@Qualifier("dsOne") DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }
  @Bean
  JdbcTemplate jdbcTemplateTwo(@Qualifier("dsTwo") DataSource dataSource){
    return new JdbcTemplate(dataSource);
  }
}
