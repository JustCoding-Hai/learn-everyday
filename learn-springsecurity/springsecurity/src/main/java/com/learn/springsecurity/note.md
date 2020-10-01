## Spring Security学习笔记
1.添加了SpringSecurity依赖后就自动将所有的接口保护起来，一开始可以通过默认的密码进行验证
### 一、手动配置用户名和密码
#### 1.application.properties配置
例：
```properties
spring.security.user.name=hai
spring.security.user.password=123
spring.security.user.roles=admin
```
#### 2.（java类）通过AuthenticationManagerBuilder配置
继承WebSecurityConfigurerAdapter类，重写configure(AuthenticationManagerBuilder auth)方法
还要使用过期的NoOpPasswordEncoder配置不对进行密码加密。
```java
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {
  //配置不进行密码加密
  @Bean
  PasswordEncoder passwordEncoder(){
    return NoOpPasswordEncoder.getInstance();
  }
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()//基于内存配置进行验证
            .withUser("hai").password("123").roles("admin")
            .and()
            .withUser("lin").password("520").roles("user");
  }
}
```
### 二、HttpSecurity配置
配置不同访问路径需要的角色
```java
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .antMatchers("admin/**").hasRole("admin")
            .antMatchers("user/**").hasAnyRole("admin","user")
            //.antMatchers("user/**").access("hasAnyRole('admin','user')")//另一种写法     
            .anyRequest().authenticated()//其他路径登录后就能访问
            .and()
            .formLogin()
            .loginProcessingUrl("/doLogin")//表单登录接口
            .permitAll()
            .and()
            .csrf().disable();//关闭csrf攻击的防御
  }
}
```
登录表单详细配置
```java
@Configuration
public class securityConfig extends WebSecurityConfigurerAdapter {
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
             /*
             成功登录后的处理
              */
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
              /*
              登录失败后的处理
                */
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
             /*
             退出账号
              */
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
```
如何配置多个HttpSecurity？
```java
@Configuration
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

```
在配置类中使用静态内部类的方式，继承WebSecurityConfigurerAdapter，重写configure方法，通过这种方式配置多个HttpSecurity。

### 三、密码加密
使用BCryptPasswordEncoder加密器，其实现了PasswordEncoder接口,使用 BCrypt 强哈希函数，
开发者在使用时可以选择提供 strength 和 SecureRandom 实例。
strength 越大，密钥的迭代次数越多，密钥迭代次数为 2^strength。strength 取值在 4~31 之间，默认为 10。
```java
public interface PasswordEncoder {
  String encode(CharSequence var1);
  boolean matches(CharSequence var1, String var2);
  default boolean upgradeEncoding(String encodedPassword) {
    return false;
  }
}
```
1. encode 方法用来对明文密码进行加密，返回加密之后的密文。
2. matches 方法是一个密码校对方法，在用户登录的时候，将用户传来的明文密码和数据库中保存的密文密码作为参数，
   传入到这个方法中去，根据返回的 Boolean 值判断用户密码是否输入正确。
3. upgradeEncoding 是否还要进行再次加密，这个一般来说就不用了。

### 四、方法安全
控制方法级别的访问权限
配置类中使用注解@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true )启动
，默认关闭。

方法配置示例：
```java
@Service
public class HelloService {
  @PreAuthorize("hasRole('admin')")
  public String admin(){
    return "Hello admin";
  }
  @Secured("ROLE_user")
  public String user(){
    return "Hello user";
  }
  @PreAuthorize("hasAnyRole('admin','user'")
  public String hello(){
    return "Helo";
  }
}
```

### 五、基于数据库认证
### 六、角色继承
### 七、动态配置权限
### Spring Security结合OAuth2


