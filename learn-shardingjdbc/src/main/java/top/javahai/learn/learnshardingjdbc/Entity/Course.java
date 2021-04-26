package top.javahai.learn.learnshardingjdbc.Entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

/**
 * @Author: huangjinhai
 * @Date: 2021\4\26 0026-14:37
 */
@Data
@Builder
public class Course {
    @TableId
    private long id;
    private String name;
    private String userId;
    private String status;
}
