package top.javahai.learnmybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.javahai.learnmybatisplus.mapper")
public class LearnMybatisplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnMybatisplusApplication.class, args);
    }

}
