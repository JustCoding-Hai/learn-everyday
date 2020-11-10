package top.javahai.learnmybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 配置类
 * @create 2020/11/10 - 23:26
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("top.javahai.learnmybatisplus.mapper")
public class MyBatisPlusConfig {
    /**
     * 注册乐观锁插件
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
