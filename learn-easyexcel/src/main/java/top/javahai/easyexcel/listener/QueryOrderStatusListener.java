package top.javahai.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.util.StringUtils;
import top.javahai.easyexcel.entity.OrderData;
import top.javahai.easyexcel.entity.QueryOrderStatus;
import top.javahai.easyexcel.util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class QueryOrderStatusListener extends AnalysisEventListener<QueryOrderStatus> {


    private ArrayList<QueryOrderStatus> list = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list01 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list02 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list03 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list04 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list05 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list06 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list07 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list08 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list09 = new ArrayList<QueryOrderStatus>();
    private ArrayList<QueryOrderStatus> list00 = new ArrayList<QueryOrderStatus>();

    @Override
    public void invoke(QueryOrderStatus orderData, AnalysisContext analysisContext) {
        list.add(orderData);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println("总行数："+list.size());
        StringBuilder sql = new StringBuilder();
        for (QueryOrderStatus queryOrderStatus : list) {
            String orderNo = queryOrderStatus.getOrderNo();
            char[] chars = orderNo.toCharArray();
            Integer remainder = Integer.valueOf(orderNo.substring(chars.length-1,chars.length));
            switch (remainder) {
                case 0:
                    list00.add(queryOrderStatus);
                    break;
                case 1:
                    list01.add(queryOrderStatus);
                    break;
                case 2:
                    list02.add(queryOrderStatus);
                    break;
                case 3:
                    list03.add(queryOrderStatus);
                    break;
                case 4:
                    list04.add(queryOrderStatus);
                    break;
                case 5:
                    list05.add(queryOrderStatus);
                    break;
                case 6:
                    list06.add(queryOrderStatus);
                    break;
                case 7:
                    list07.add(queryOrderStatus);
                    break;
                case 8:
                    list08.add(queryOrderStatus);
                    break;
                case 9:
                    list09.add(queryOrderStatus);
                    break;
            }

        }
            StringBuilder orderNoList00 = new StringBuilder();
            list00.forEach(data->{
                orderNoList00.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_0 where order_no in ("+orderNoList00+");");
            System.out.println();

            StringBuilder orderNoList01 = new StringBuilder();
            list01.forEach(data->{
                orderNoList01.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_1 where order_no in ("+orderNoList01+");");
            System.out.println();

            StringBuilder orderNoList02 = new StringBuilder();
            list02.forEach(data->{
                orderNoList02.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_2 where order_no in ("+orderNoList02+");");
            System.out.println();

            StringBuilder orderNoList03 = new StringBuilder();
            list03.forEach(data->{
                orderNoList03.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_3 where order_no in ("+orderNoList03+");");
            System.out.println();

            StringBuilder orderNoList04 = new StringBuilder();
            list04.forEach(data->{
                orderNoList04.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_4 where order_no in ("+orderNoList04+")");
            System.out.println();

            StringBuilder orderNoList05 = new StringBuilder();
            list05.forEach(data->{
                orderNoList05.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_5 where order_no in ("+orderNoList05+");");
            System.out.println();

            StringBuilder orderNoList06 = new StringBuilder();
            list06.forEach(data->{
                orderNoList06.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_6 where order_no in ("+orderNoList06+");");
            System.out.println();

            StringBuilder orderNoList07 = new StringBuilder();
            list07.forEach(data->{
                orderNoList07.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_7 where order_no in ("+orderNoList07+");");
            System.out.println();

            StringBuilder orderNoList08 = new StringBuilder();
            list08.forEach(data->{
                orderNoList08.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_8 where order_no in ("+orderNoList08+");");
            System.out.println();

            StringBuilder orderNoList09 = new StringBuilder();
            list09.forEach(data->{
                orderNoList09.append("'").append(data.getOrderNo()).append("',");
            });
            System.out.println("select order_no,order_status from t_qm_app_order_new_9 where order_no in ("+orderNoList09+");");
            System.out.println();

//        try {
//            String txtFile = FileUtils.creatTxtFile("D:\\", "updateSQL");
//            System.out.println("文件路径："+txtFile);
//            System.out.println(sql);
//
//            FileUtils.writeTxtFile(txtFile, sql.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
