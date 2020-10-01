package com.learn.websocket02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Hai
 * @date 2020/6/5 - 17:41
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  //配置加密方案
  @Bean
  PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
  //配置用户

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("admin")
            .password("123")
            .roles("admin")
            .and()
            .withUser("hai")
            .password("123")
            .roles("user");
  }

  //配置访问
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .anyRequest().authenticated()
            .and()
            .formLogin().permitAll();
  }
}
