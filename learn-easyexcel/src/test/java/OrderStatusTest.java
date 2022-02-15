import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import top.javahai.easyexcel.entity.OrderData;
import top.javahai.easyexcel.entity.QueryOrderStatus;
import top.javahai.easyexcel.listener.OrderDataListener;
import top.javahai.easyexcel.listener.QueryOrderStatusListener;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class OrderStatusTest {
    @Test
    public void simpleRead(){
        String fileName="C:\\Users\\Administrator\\Downloads\\order-214.xlsx";

        EasyExcel.read(fileName, QueryOrderStatus.class,new QueryOrderStatusListener()).sheet().doRead();
    }
}
