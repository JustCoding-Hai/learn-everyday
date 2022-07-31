package top.javahai.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
@Data
public class LogData {
    @ExcelProperty(value="__tag__:__hostname__",index = 0)
    private String hostName;
    @ExcelProperty(value="__tag__:__path__",index = 1)
    private String path;
    @ExcelProperty(value="__tag__:__receive_time__",index = 2)
    private String receiveTime;
    @ExcelProperty(value="__topic__",index = 3)
    private String topic;
    @ExcelProperty(value="content",index = 4)
    private String content;


}
