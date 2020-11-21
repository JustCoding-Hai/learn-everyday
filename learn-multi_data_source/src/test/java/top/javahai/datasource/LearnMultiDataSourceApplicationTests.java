package top.javahai.datasource;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.datasource.entity.Admin;
import top.javahai.datasource.entity.AdminInquiryDO;
import top.javahai.datasource.entity.TUserinfo;
import top.javahai.datasource.service.impl.AdminServiceImpl;
import top.javahai.datasource.service.impl.TUserinfoServiceImpl;

import java.util.List;

@SpringBootTest
@MapperScan(basePackages="top.javahai.datasource.mapper")
class LearnMultiDataSourceApplicationTests {

	@Autowired
	AdminServiceImpl adminService;

	@Autowired
	TUserinfoServiceImpl tUserinfoService;

	@Test
	void contextLoads() {
		Admin admin = adminService.selectAdminById(1);
		System.out.println(admin);

		TUserinfo tUserinfo = tUserinfoService.selectUserById("01");
		System.out.println(tUserinfo);
	}

	@Test
	public void testCastException(){
		List<AdminInquiryDO> adminInquiryDOS = adminService.selectAll();
		System.out.println(adminInquiryDOS);
		//adminInquiryDOS.stream().forEach( System.out::println);
	}



}
