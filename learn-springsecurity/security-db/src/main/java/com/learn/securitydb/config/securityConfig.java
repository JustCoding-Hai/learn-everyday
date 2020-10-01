package com.learn.securitydb.config;

import com.learn.securitydb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import sun.security.krb5.Config;

/**
 * @author Hai
 * @date 2020/4/10 - 16:25
 */
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userService);
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("admin")
            .antMatchers("/dba/**").hasRole("dba")
            .antMatchers("/user/**").hasAnyRole("user")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout");
  }
  //角色继承
  @Bean
  RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy="ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return  roleHierarchy;
  }
}
