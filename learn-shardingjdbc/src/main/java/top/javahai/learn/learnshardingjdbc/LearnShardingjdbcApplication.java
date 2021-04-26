package top.javahai.learn.learnshardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("top.javahai.learn.learnshardingjdbc.dao")
public class LearnShardingjdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnShardingjdbcApplication.class, args);
    }

}
