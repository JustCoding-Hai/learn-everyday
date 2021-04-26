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
public class Course {
    private Long id;
    private String name;
    private Long userId;
    private String status;
}
