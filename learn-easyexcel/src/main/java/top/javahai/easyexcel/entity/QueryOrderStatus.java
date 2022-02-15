package top.javahai.easyexcel.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author huangjinhai
 * @date 2022\2\14 0014
 */
@Data
public class QueryOrderStatus {
    @ExcelProperty(value="订单号",index = 0)
    private String orderNo;
}
