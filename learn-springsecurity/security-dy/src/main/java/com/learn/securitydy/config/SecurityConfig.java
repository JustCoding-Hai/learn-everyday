package com.learn.securitydy.config;

import com.learn.securitydy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author Hai
 * @date 2020/4/11 - 13:17
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Autowired
  UserService userService;
  @Autowired
  MyFilter myFilter;
  @Autowired
  MyAccessDecisionManager myAccessDecisionManager;

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
              @Override
              public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setAccessDecisionManager(myAccessDecisionManager);
                o.setSecurityMetadataSource(myFilter);
                return o;
              }
            })
            .and()
            .formLogin()
            .permitAll()
            .and()
            .csrf().disable();
  }
}
