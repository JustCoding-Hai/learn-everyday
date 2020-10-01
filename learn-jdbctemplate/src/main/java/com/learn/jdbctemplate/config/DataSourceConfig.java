package com.learn.jdbctemplate.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Hai
 * @date 2020/2/11 - 17:17
 */
@Configuration
public class DataSourceConfig {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.one")
  DataSource dsOne(){
    return DruidDataSourceBuilder.create().build();
  }
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.two")
  DataSource dsTwo(){
    return DruidDataSourceBuilder.create().build();
  }
}
