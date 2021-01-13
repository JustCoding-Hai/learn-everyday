package top.javahai.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;
import top.javahai.easyexcel.entity.ExcelStudentData;

import javax.lang.model.element.VariableElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2021/1/13 - 23:29
 **/
@Slf4j
public class ExcelStudentDataListener extends AnalysisEventListener<ExcelStudentData> {

    /**
     * 每隔五条执行数据库插入
     */
    private static final int BATCH_COUNT=5;

    List<ExcelStudentData> list=new ArrayList<>();

    @Override
    public void invoke(ExcelStudentData excelStudentData, AnalysisContext analysisContext) {
        log.info("读取到一条数据：{}",excelStudentData.toString());
        list.add(excelStudentData);
        if (list.size()>BATCH_COUNT){
            log.info("执行数据库操作");
            list.clear();
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        log.info("执行数据库操作将剩余的数据保存到数据库中");


    }
}
