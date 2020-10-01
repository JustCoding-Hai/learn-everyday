package com.learn.securityjson.config;

import com.learn.securityjson.filter.MyAuthenticationFilter;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Hai
 * @date 2020/4/12 - 13:40
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{
  @Bean
  MyAuthenticationFilter myAuthenticationFilter() throws Exception{
    MyAuthenticationFilter myFilter = new MyAuthenticationFilter();
    myFilter.setAuthenticationManager(authenticationManagerBean());
    return myFilter;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().anyRequest().authenticated()
            .and()
            .formLogin().permitAll()
            .and()
            .csrf().disable();
    http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);//配置自定义的过滤器
  }
}
