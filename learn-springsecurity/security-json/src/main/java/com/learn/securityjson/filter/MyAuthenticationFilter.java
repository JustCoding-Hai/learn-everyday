package com.learn.securityjson.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author Hai
 * @date 2020/4/12 - 13:19
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }
    //如果用户使用json形式来传递参数
    if (request.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
      String username = null;
      String password = null;
      //将请求转换为Map类型的key/value
      try {
        Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        username=map.get("username");
        password=map.get("password");
      } catch (IOException e) {
        e.printStackTrace();
      }


      if (username == null) {
        username = "";
      }

      if (password == null) {
        password = "";
      }

      username = username.trim();
      UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
      this.setDetails(request, authRequest);
      return this.getAuthenticationManager().authenticate(authRequest);
    }
    //如果不是json形式就调用父类的原方法
    return super.attemptAuthentication(request, response);
  }
}
