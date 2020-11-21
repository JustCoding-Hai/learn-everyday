package top.javahai.datasource.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import top.javahai.datasource.entity.AdminInquiryDO;
import top.javahai.datasource.service.impl.AdminServiceImpl;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ethan
 * @since 2020-11-19
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminServiceImpl adminService;

    @GetMapping("/list")
    public List<AdminInquiryDO> list(){
        return adminService.selectAll();
    }
}
