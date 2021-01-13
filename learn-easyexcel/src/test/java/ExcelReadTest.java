import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import top.javahai.easyexcel.entity.ExcelStudentData;
import top.javahai.easyexcel.listener.ExcelStudentDataListener;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/13 - 23:36
 **/
public class ExcelReadTest  {

    @Test
    public void simpleRead(){
        String fileName="d:/data/excel/01-simpleread.xlsx";
        EasyExcel.read(fileName, ExcelStudentData.class,new ExcelStudentDataListener()).sheet().doRead();
    }
}
