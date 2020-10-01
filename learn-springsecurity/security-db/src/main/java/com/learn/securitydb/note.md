## Spring Security基于数据库进行认证
### 首先配置环境
1.添加MySQL Driver，Spring Security，Spring Web，myBatis依赖
2.控制MySQL Driver版本，添加数据库池依赖druid
3.application.properties中配置数据库信息
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.username=root
spring.datasource.password=123456
```
### 创建实体类
创建两个实体类：User类和Role类
User类实现UserDetails接口
```java
public class User implements UserDetails {
  private Integer id;
  private String username;
  private String password;
  private Boolean enabled;//是否可用
  private Boolean locked;//是否锁定
  private List<Role> roles;//拥有的角色

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }


  public void setLocked(Boolean locked) {
    this.locked = locked;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {//返回用户所有的角色
    //先去数据进行处理
    List<SimpleGrantedAuthority> authorities=new ArrayList();
    for (Role role : roles) {
      authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
    }
    return authorities;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {//账号是否未过期
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {//账号是否未锁定
    return locked;
  }

  @Override
  public boolean isCredentialsNonExpired() {//凭证是否未过期
    return true;
  }
  @Override
  public boolean isEnabled() {//是否能使用
    return enabled;
  }
  public void setPassword(String password) {
    this.password = password;
  }
}
```
Role类：
```java
public class Role {
  private Integer id;
  private String name;//角色名
  private String nameZh;//角色中文名

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameZh() {
    return nameZh;
  }

  public void setNameZh(String nameZh) {
    this.nameZh = nameZh;
  }
}

```
### 配置Mapper层
```java
@Mapper
public interface UserMapper {
  User loadUserByUsername(String username);//根据用户名查询用户
  List<Role> getUserRoleById(Integer id);//获取用户角色
}
```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.learn.securitydb.mapper.UserMapper">

    <select id="loadUserByUsername" resultType="com.learn.securitydb.bean.User">
        select * from user where username=#{username}
    </select>
    
    <select id="getUserRoleById" resultType="com.learn.securitydb.bean.Role">
        select * from role where id in (select rid from user_role where uid=#{id})
    </select>
</mapper>
```
  因为Spring Boot默认不加载src/main/java下的静态文件，所以在pom.xml中要进行如下配置：
```xml
   <build>
<!--        配置静态资源路径-->
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.xml</include>
                </includes>
            </resource>
            <resource>
                <directory>src/main/resources</directory>
            </resource>
        </resources>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

```
 ### 配置service层
继承UserDetailsService接口
```java
@Service
public class UserService implements UserDetailsService {
  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user=userMapper.loadUserByUsername(username);
    if (user==null){
      throw  new UsernameNotFoundException("用户不存在！");
    }
    user.setRoles(userMapper.getUserRoleById(user.getId()));
    return user;
  }
}
```
java类配置SpringSecurity
```java
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
            .antMatchers("/user/**").hasAnyRole("admin","dba")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout");
  }
}

```
### Controller层
最后配置Controller层，测试代码,不同角色的访问效果
```java
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String hello(){
    return "hello security";
  }
  @GetMapping("admin/hello")
  public String admin(){
    return "hello admin";
  }
  @GetMapping("dba/hello")
  public String dba(){
    return "hello dba";
  }
  @GetMapping("user/hello")
  public String user(){
    return "hello user";
  }
}

```
### 角色继承
```java
  //角色继承，dba有所有权限，admin有自身的和user的权限。
  @Bean
  RoleHierarchy roleHierarchy(){
    RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
    String hierarchy="ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
    roleHierarchy.setHierarchy(hierarchy);
    return  roleHierarchy;
  }
```



