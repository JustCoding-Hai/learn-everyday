package com.learn.springsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Hai
 * @date 2020/4/8 - 18:44
 * 多HttpSecurity配置
 */
//@Configuration
public class MultiHttpSecurityConfig {
  //配置不进行密码加密
  @Bean
  PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }

  @Autowired
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()//基于内存配置进行验证
            .withUser("hai").password("123").roles("admin")
            .and()
            .withUser("lin").password("520").roles("user");
  }

  @Configuration
  @Order(1)
  public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .antMatchers("/admin/**").hasRole("admin")
              .anyRequest().authenticated();
    }
  }

  @Configuration
  @Order
  public  static class OtherSecurityConfig extends  WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.authorizeRequests()
              .and()
              .formLogin()
              .loginProcessingUrl("/doLogin")
              .permitAll()
              .and()
              .csrf().disable();

    }
  }

}
