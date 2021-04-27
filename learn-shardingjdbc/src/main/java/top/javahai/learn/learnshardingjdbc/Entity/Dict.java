package top.javahai.learn.learnshardingjdbc.Entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/4/27 - 23:38
 **/
@Data
@TableName("t_dict")
public class Dict {
    private Long id;
    private String dictCode;
    private String dictName;
}
