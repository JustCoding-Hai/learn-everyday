package top.javahai.learnmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.util.Date;

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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill= FieldFill.INSERT_UPDATE)
    private Date updateTime;
    @Version
    @TableField(fill =FieldFill.INSERT)
    private Integer version;

    @TableLogic
    private Integer deleted;


}
