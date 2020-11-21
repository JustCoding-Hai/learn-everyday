package top.javahai.datasource.annotation;

import top.javahai.datasource.config.DataSourceType;

import java.lang.annotation.*;

/**
 * @author Ethan Huang
 * @create 2020-11-19 14:32
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SpecifyDataSource {

    /**
     * @return
     */
    DataSourceType value() default DataSourceType.CHATROOM;

}
