package top.javahai.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import top.javahai.easyexcel.entity.LogData;
import top.javahai.easyexcel.entity.OrderData;
import top.javahai.easyexcel.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class OrderDataListener extends AnalysisEventListener<OrderData> {


    private ArrayList<OrderData> list = new ArrayList<OrderData>();

    @Override
    public void invoke(OrderData orderData, AnalysisContext analysisContext) {
        list.add(orderData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("总行数："+list.size());
        StringBuilder sql = new StringBuilder();
        for (OrderData orderData : list) {
            sql.append("db.trade.update({'_id':").append("'").append(orderData.getOrderNo()).append("'},")
                    .append("{$set:{'tradeState.logisticState':'UNRECEIVE',")
                    .append("'tradeDelivers':[]}});\r\n");
        }
        try {
            String txtFile = FileUtils.creatTxtFile("D:\\", "updateSQL");
            System.out.println("文件路径："+txtFile);
            System.out.println(sql);

            FileUtils.writeTxtFile(txtFile, sql.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //备份语句
        StringBuilder backupSql = new StringBuilder();
        backupSql.append("db.trade.find({'_id':{$in:[");
        for (OrderData orderData : list) {
            backupSql.append("").append(orderData.getOrderNo()).append(",");
        }
        backupSql.append("]}});");
        System.out.println(backupSql);
    }
}
