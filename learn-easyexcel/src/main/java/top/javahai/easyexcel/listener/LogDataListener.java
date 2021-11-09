package top.javahai.easyexcel.listener;

import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import top.javahai.easyexcel.entity.LogData;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author huangjinhai
 * @date 2021\11\8 0008
 */
public class LogDataListener extends AnalysisEventListener<LogData> {


    private ArrayList<User> list = new ArrayList<User>();

    @Override
    public void invoke(LogData logData, AnalysisContext analysisContext) {
        Pattern pattern = Pattern.compile("\"user\":\"\\d+\"");
        Matcher matcher = pattern.matcher(logData.getHostName());
        while(matcher.find()){
            String group = matcher.group();
            User user = com.alibaba.fastjson.JSON.parseObject("{"+group+"}", User.class);
            list.add(user);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        System.out.println(list.size());
        StringBuilder builder = new StringBuilder();
        list.forEach(user->{
            builder.append(user.getUserId()).append(",");
        });
        System.out.println(builder.toString());
    }

    @Data
    public static class User{
        @JSONField(name = "user")
        private String userId;
    }
}
