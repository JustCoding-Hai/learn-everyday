package top.javahai.learn_swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Hai
 * @date 2020/7/26 - 11:21
 */
@Configuration
@EnableSwagger2//开启Swagger
public class SwaggerConfig {

  @Bean
  public Docket docketA(){
    return new Docket(DocumentationType.SWAGGER_2).groupName("A");
  }
  @Bean
  public Docket docketB(){
    return new Docket(DocumentationType.SWAGGER_2).groupName("B");
  }  @Bean
  public Docket docketC(){
    return new Docket(DocumentationType.SWAGGER_2).groupName("C");
  }

  /**
   * Docket对象是Swagger的实例对象，通过自定义一个Docket进行自主配置
   * @return
   */
  @Bean
  public Docket docket(Environment environment){
    /**
     * 配置swagger显示的开关：
     * 1.设置要在哪个Profile显示swagger
     * 2.判断当前环境是否在swagger要显示的范围之内
     * 3.enable()方法传入boolean值进行控制是否显示
     */
    //设置要显示的swagger的环境
    Profiles profiles = Profiles.of("dev", "test");
    //当前环境是否在swagger的显示范围内
    boolean acceptsProfiles = environment.acceptsProfiles(profiles);

    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            //通过select()方法配置如何扫描接口
            .enable(acceptsProfiles)
            .select()
            /**
             * 通过apis方法执行接口扫描的规则
             *  1.执行扫描的包
             *    .apis(RequestHandlerSelectors.basePackage(""))
             *  2.全部接口都扫描
             *     .apis(RequestHandlerSelectors.any())
             *  3.全部接口都不扫描
             *     .apis(RequestHandlerSelectors.none())
             *  4.扫描类上有指定注解的类中的接口
             *     .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
             *  5.扫描方法上有指定注解的接口
             *     .apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class))
             **/
            //通过apis方法，传入RequestHandSelectors进行配置，basePackage指示要扫描的包
            .apis(RequestHandlerSelectors.basePackage("top.javahai.learn_swagger.controller"))
            /**
             * 通过paths方法进行接口url扫描过滤
             * 传入PathSelectors的执行方法
             * 1.any()//任何接口都扫描
             * 2.none()//任何接口都不扫描
             * 3.ant(final String antPattern)//通过ant进行控制
             * 4.regex(final String pathRegex)//通过正则表达式控制
             */
            //配置只扫描接口url是“/user/”开头的接口
            .paths(PathSelectors.ant("/user/**"))
            .build();
  }

  /**
   * 配置Swagger的文档信息
   * @return
   */
 private ApiInfo apiInfo(){
   Contact contact = new Contact("hai", "https://blog.csdn.net/huangjhai", "1258398543@qq.com");
   return new ApiInfo(
            "Learn Swagger Api Documentation",//标题
            "Api Documentation接口文档",//描述
            "1.0.0",//版本
            "https://blog.csdn.net/huangjhai",//组织链接
            contact,//联系人信息
            "Apache 2.0",//许可
            "http://www.apache.org/licenses/LICENSE-2.0",//许可的链接
            new ArrayList(Arrays.asList("Oracle")));//供应商拓展列表
 }

}
