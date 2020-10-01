package com.learn.mybatis.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author Hai
 * @date 2020/2/12 - 15:58
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
