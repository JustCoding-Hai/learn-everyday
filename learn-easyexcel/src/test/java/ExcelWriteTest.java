import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.junit.Test;
import top.javahai.easyexcel.entity.ExcelStudentData;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/13 - 19:05
 **/
public class ExcelWriteTest {


    /**
     * 写Excel操作推荐使用Excel 2007，因为Excel2003最多一次写65535行且占用内存大
     */
    @Test
    public void simpleWrite2007(){
//       String fileName="d:/data/excel/03-simplewrite.xlsx";
       String fileName="d:/data/excel/01-simpleread.xlsx";
        EasyExcel.write(fileName, ExcelStudentData.class).sheet("学生表").doWrite(data());
    }

    /**
     * 如果需要写2003版Excel，需要通过excelType版本指定Excel类型
     */
    @Test
    public void simpleWrite2003(){
        String fileName="d:/data/excel/02-simplewrite.xls";
        EasyExcel.write(fileName, ExcelStudentData.class)
                .excelType(ExcelTypeEnum.XLS)
                .sheet("学生表")
                .doWrite(data());
    }

    private List data() {
        ArrayList<ExcelStudentData> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ExcelStudentData studentData = new ExcelStudentData();
            studentData.setName("张"+i);
            studentData.setBirthday(new Date());
            studentData.setPassword("123");
            studentData.setSalary(9200.59);
            list.add(studentData);
        }
        return list;
    }



}
