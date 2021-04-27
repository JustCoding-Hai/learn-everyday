package top.javahai.learn.learnshardingjdbc.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: huangjinhai
 * @Date: 2021\4\27 0027-10:28
 */
@Data
@Builder
@TableName("t_user")
public class User {
    private Long id;
    private String username;
    private String status;
}
