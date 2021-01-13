package top.javahai.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/13 - 19:03
 **/
@Data
public class ExcelStudentData {
    @ExcelProperty(value="姓名",index = 0)
    private String name;

    @ExcelProperty(value = "生日",index = 2)
    @DateTimeFormat("yyyy年MM月dd日")
    private Date birthday;

    @ExcelProperty(value = "薪资",index = 4)
    @NumberFormat("#.#")
    private Double salary;

    /**
     * 忽略这个字段，不进行忽略默认会读取，及时没有加ExcelProperty注解
     */
    @ExcelIgnore
    private String password;

}
