package top.javahai.learnmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.learnmybatisplus.entity.User;
import top.javahai.learnmybatisplus.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: mybatisplus的查询操作
 * @create 2020/11/14 - 20:50
 **/
@SpringBootTest
public class SelectTests {
    @Autowired
    private UserMapper userMapper;
    /**
     *通过多个id批量查询,完成MyBatis的动态SQL标签的Foreach功能
     */
    @Test
    public void testSelectBatchIds(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3, 4, 5));
        users.forEach(System.out::println);
    }
    /**
     * 简单的条件查询，通过Map封装查询条件
     */
    @Test
    public void testSelectByMao(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","tom");
        map.put("age",28);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    /**
     * 测试分页
     */
    @Test
    public void testSelectPage(){
        Page<User> userPage = new Page<>(1,5);
        Page<User> userIPage = (Page<User>) userMapper.selectPage(userPage, null);

        userIPage.getRecords().forEach(System.out::println);
        System.out.println(userIPage.getCurrent());
        System.out.println(userIPage.getPages());
        System.out.println(userIPage.getTotal());
        System.out.println(userIPage.hasNext());
        System.out.println(userIPage.hasPrevious());

    }

    /**
     * 返回指定的列
     */
    @Test
    public void testSelectMapsPage(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age", "email");
        Page<Map<String, Object>> page = new Page<>(1, 5);
        Page<Map<String, Object>> userPage = userMapper.selectMapsPage(page,queryWrapper);
        List<Map<String, Object>> records = userPage.getRecords();
        records.forEach(System.out::println);

    }

}
