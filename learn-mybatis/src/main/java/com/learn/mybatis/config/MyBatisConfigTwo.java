package com.learn.mybatis.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author Hai
 * @date 2020/2/12 - 16:18
 */
@Configuration
@MapperScan(basePackages = "com.learn.mybatis.mapper2",sqlSessionFactoryRef ="sqlSessionFactory2" ,sqlSessionTemplateRef ="sqlSessionTemplate2" )
public class MyBatisConfigTwo {
  @Resource(name = "dsTwo")
  DataSource dsTwo;

  @Bean
  SqlSessionFactory sqlSessionFactory2() {
    SqlSessionFactory sessionFactory = null;
    try {
      SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
      bean.setDataSource(dsTwo);
      sessionFactory = bean.getObject();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return sessionFactory;
  }
  @Bean
  SqlSessionTemplate sqlSessionTemplate2() {
    return new SqlSessionTemplate(sqlSessionFactory2());
  }


}
