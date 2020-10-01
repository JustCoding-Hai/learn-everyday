package com.learn.springsecurity.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.print.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Hai
 * @date 2020/4/7 - 16:23
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true )
public class securityConfig extends WebSecurityConfigurerAdapter {
//  配置不进行密码加密
//  @Bean
//  PasswordEncoder passwordEncoder(){
//    return NoOpPasswordEncoder.getInstance();
//  }
  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()//基于内存配置进行验证
            .withUser("hai").password("$2a$10$cP1wEeFGErLaitZRcMET4uBm0ZTX3nLU/nnmIoQcB46mWAy.YuQkG").roles("admin")
            .and()
            .withUser("lin").password("$2a$10$88dN.aPKLQYjxjd1eYq7auWOMm0S/fehZZ9.T3iJZvfRbQNyuyeu.").roles("user");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("/admin/**").hasRole("admin")
            .antMatchers("/user/**").hasAnyRole("admin","user")
            //.antMatchers("user/**").access("hasAnyRole('admin,user')")//另一种写法
            .anyRequest().authenticated()//其他路径登录后就能访问
            .and()
            .formLogin()
            .loginProcessingUrl("/doLogin")//表单登录接口
            .loginPage("/login")//登录页面
            .usernameParameter("user")//用户名的参数名，默认为username
            .passwordParameter("pwd")//密码的参数名，默认为password
            .successHandler(new AuthenticationSuccessHandler() {//处理登录成功
              @Override
              public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp,
                                                  Authentication authentication) throws IOException, ServletException {
                //authentication保持了登录成功后的用户信息
               resp.setContentType("application/json;charset=utf-8");
               PrintWriter out=resp.getWriter();
                Map<String,Object> map=new HashMap<>();
                map.put("status",200);
                map.put("msg",authentication.getPrincipal());//用户凭证
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
              }
            })
            .failureHandler(new AuthenticationFailureHandler() {//处理登录失败
              @Override
              public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                //e：异常信息
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out=resp.getWriter();
                Map<String,Object> map=new HashMap<>();
                map.put("status",401);
                if (e instanceof LockedException){
                  map.put("msg","账户被锁定，登录失败");
                }else if (e instanceof BadCredentialsException){
                  map.put("msg","用户名或密码错误，登录失败");
                }else if (e instanceof DisabledException){
                  map.put("msg","账户被禁用，登录失败");
                }else if (e instanceof AccountExpiredException){
                  map.put("msg","账户过期，登录失败");
                }else if (e instanceof CredentialsExpiredException){
                  map.put("msg","密码过期，登录失败");
                }else {
                  map.put("msg","登录失败");
                }
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
              }
            })
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessHandler(new LogoutSuccessHandler() {
              @Override
              public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out=resp.getWriter();
                Map<String,Object> map=new HashMap<>();
                map.put("status",200);
                map.put("msg","退出成功！");
                out.write(new ObjectMapper().writeValueAsString(map));
                out.flush();
                out.close();
              }
            })

            .and()
            .csrf().disable();//关闭csrf攻击的防御
  }
}
