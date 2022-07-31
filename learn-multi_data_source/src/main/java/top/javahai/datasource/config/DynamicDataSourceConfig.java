package top.javahai.datasource.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ethan Huang
 * @create 2020-11-19 14:47
 */
@Configuration
public class DynamicDataSourceConfig {

    @Bean(name="chatroom")
    @ConfigurationProperties("spring.datasource.druid.first")
    public DataSource dataSource1(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name ="book_db")
    @ConfigurationProperties("spring.datasource.druid.second")
    public DataSource dataSource2(){
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name="mydb")
    @ConfigurationProperties("spring.datasource.druid.third")
    public DataSource dataSource3(){
        return DruidDataSourceBuilder.create().build();
    }


    @Bean(name="dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put(DataSourceTypeEnum.CHATROOM.getName(), dataSource1());
        targetDataSources.put(DataSourceTypeEnum.BOOK_DB.getName(), dataSource2());
        targetDataSources.put(DataSourceTypeEnum.MY_DB.getName(), dataSource3());
        return new DynamicDataSource(dataSource1(), targetDataSources);
    }


}
