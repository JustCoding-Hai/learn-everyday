import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import top.javahai.easyexcel.entity.ExcelStudentData;
import top.javahai.easyexcel.entity.LogData;
import top.javahai.easyexcel.listener.ExcelStudentDataListener;
import top.javahai.easyexcel.listener.LogDataListener;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class LogDataTest {
    @Test
    public void simpleRead(){
        String fileName="C:\\Users\\Administrator\\Downloads\\log_data2.xlsx";

        EasyExcel.read(fileName, LogData.class,new LogDataListener()).sheet().doRead();
    }
}
