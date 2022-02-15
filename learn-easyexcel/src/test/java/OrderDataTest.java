import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import top.javahai.easyexcel.entity.LogData;
import top.javahai.easyexcel.entity.OrderData;
import top.javahai.easyexcel.listener.LogDataListener;
import top.javahai.easyexcel.listener.OrderDataListener;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class OrderDataTest {
    @Test
    public void simpleRead(){
        String fileName="C:\\Users\\Administrator\\Downloads\\order-20211112.xlsx";

        EasyExcel.read(fileName, OrderData.class,new OrderDataListener()).sheet().doRead();
    }
}
