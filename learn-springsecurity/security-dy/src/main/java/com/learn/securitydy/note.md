# 动态分配权限
## 基于数据库验证
### 环境配置
1.添加Spring Web，Spring Security，MySQL Driver，MyBatis依赖，

2.配置pom.xml：
锁定MySQL版本，添加druid数据库连接池的依赖.

3.配置数据库连接信息
```properties
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://localhost:3306/springboot
spring.datasource.username=root
spring.datasource.password=123456
```
### 实体类准备
1.User类

### 数据访问层
在启动类中配置mapper扫描（不用在mapper包中多次使用mapper注解）
1.定义UserMapper接口
2.UserMapper.xml
配置静态资源路径
### Service层
继承UserDetailService
### java配置类
继承WebSecurityConfigurerAdapter类


### Controller层

能够完成登录操作


## 权限配置
### 持久层
```java
public interface MenuMapper {
  List<Menu> getAllMenus();
}
```
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.learn.securitydy.mapper.MenuMapper">
    <resultMap id="BaseResultMap" type="com.learn.securitydy.bean.Menu">
        <id property="id" column="id"></id>
        <result property="pattern" column="pattern"></result>
        <collection property="roles" ofType="com.learn.securitydy.bean.Role">
            <id property="id" column="rid"></id>
            <result property="name" column="rname"></result>
            <result property="nameZh" column="rnameZh"></result>
        </collection>
    </resultMap>

    <select id="getAllMenus" resultMap="BaseResultMap">
  select m.*,r.`id` as rid, r.`name` as rname,r.`nameZh` as rnameZh from menu m left join menu_role mr on m.`id`=mr.`id` left join role r on mr.`rid`=r.`id`
    </select>
</mapper>

```
### service层
```java
@Service
public class MenuService {
  @Autowired
  MenuMapper menuMapper;

  public List<Menu> getAllMenus(){
    return menuMapper.getAllMenus();
  }
}
```
### java配置类
java配置类MyFilter，继承FilterInvocationSecurityMetadataSource。
```java
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

  AntPathMatcher antPathMatcher=new AntPathMatcher();//用于匹配路径

  @Autowired
  MenuService menuService;

  @Override
  public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
    //获取请求路径
    String requestUrl = ((FilterInvocation) o).getRequestUrl();
    //获取所有的角色对应权限列表
    List<Menu> allMenus = menuService.getAllMenus();
    for (Menu menu : allMenus) {
      if (antPathMatcher.match(menu.getPattern(),requestUrl)){//匹配请求路径
        List<Role> roles = menu.getRoles();//路径所需要的角色
        String[] rolesStr=new String[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
          rolesStr[i]=roles.get(i).getName();
        }
        return SecurityConfig.createList(rolesStr);
      }
    }

    return SecurityConfig.createList("ROLE_login"); //默认可以访问的
  }

  @Override
  public Collection<ConfigAttribute> getAllConfigAttributes() {
    return null;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}
```
java配置类，实现AccessDecisionManager接口
```java
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
  /**
   *
   * @param authentication 具有的角色
   * @param o  当前访问对象
   * @param collection 需要的角色
   * @throws AccessDeniedException
   * @throws InsufficientAuthenticationException
   */
  @Override
  public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
    for (ConfigAttribute attribute : collection) {
      if ("ROLE_login".equals(attribute.getAttribute())){
        if (authentication instanceof AnonymousAuthenticationToken){
          throw new AccessDeniedException("非法请求！");
        }
        else {
          return;
        }
      }
      /*
      这里判断当前用户是否拥有访问特定路径所需要的角色，具有一个皆可或都需要
       */
      Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
      for (GrantedAuthority authority : authorities) {
        if (authority.getAuthority().equals(attribute.getAttribute())){
          return;
        }
      }
    }
     throw new AccessDeniedException("非法请求！");
  }

  @Override
  public boolean supports(ConfigAttribute configAttribute) {
    return true;
  }

  @Override
  public boolean supports(Class<?> aClass) {
    return true;
  }
}

```
在配置类Security中配置HttpSecurity
```java
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

```
最后在控制层中提供相应接口进行测试




