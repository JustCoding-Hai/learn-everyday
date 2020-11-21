package top.javahai.datasource;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;
import top.javahai.datasource.config.DynamicDataSourceConfig;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("top.javahai.datasource.mapper")
@Import({DynamicDataSourceConfig.class})
public class LearnMultiDataSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnMultiDataSourceApplication.class, args);
	}

}
