## Spring Security使用json登录
Spring Security默认使用key/value的方式来获取用户名和密码
在UsernamePasswordAuthenticationFilter中使用request.getParameter的方式获取请求中的用户名和密码。
```java
public class UsernamePasswordAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
  //省略。。
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (this.postOnly && !request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    } else {
      String username = this.obtainUsername(request);
      String password = this.obtainPassword(request);
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
  }
  protected String obtainUsername(HttpServletRequest request) {
    return request.getParameter(this.usernameParameter);
  }
    @Nullable
    protected String obtainPassword(HttpServletRequest request) {
      return request.getParameter(this.passwordParameter);
    }
    //省略
  }
```
要使用json方式进行登录，需要继承该类来配置
```java
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
    if (!request.getMethod().equals("POST")) {
      throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
    }
    /*
    json请求处理
    MediaType.APPLICATION_JSON_VALUE
    Map<String, String> map = new ObjectMapper().readValue(request.getInputStream(), Map.class);
     */
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

```
java配置类
```java
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
```
提供接口访问
```java
@RestController
public class HelloController {
  @GetMapping("/hello")
  public String hello(){
    return "Hello Security";
  }
}
```
