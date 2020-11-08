package top.javahai.learnmybatisplus.entity;

import lombok.Data;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 用户
 * @create 2020/11/9 - 0:03
 **/
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
