package top.javahai.datasource.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.javahai.datasource.entity.*;
import top.javahai.datasource.service.impl.AdminServiceImpl;
import top.javahai.datasource.service.impl.TUserServiceImpl;
import top.javahai.datasource.service.impl.TUserinfoServiceImpl;

import java.util.List;

/**
 * @author Hai
 * @program: learn-everyday
 * @description:
 * @create 2022/7/31 - 14:00
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private AdminServiceImpl adminService;

    @Autowired
    private TUserinfoServiceImpl userinfoService;

    @Autowired
    private TUserServiceImpl userService;



    @GetMapping("/list")
    public UserVO list(){
        List<Admin> adminList= adminService.getAll();
        List<TUserinfo> tUserinfos = userinfoService.selectAll();
        List<TUser> tUsers = userService.selectAll();
        UserVO userVO = new UserVO();
        userVO.setAdminList(adminList);
        userVO.settUserinfos(tUserinfos);
        userVO.settUsers(tUsers);
        return userVO;
    }
}
