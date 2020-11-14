package top.javahai.learnmybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.learnmybatisplus.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: mybatis-plus删除操作
 * @create 2020/11/14 - 20:48
 **/
@SpringBootTest
public class DeleteTests {

    @Autowired
    private UserMapper userMapper;

    /**
     * 根据id删除数据
     */
    @Test
    public void testDeleteById(){
        int i = userMapper.deleteById(1326186009754361858L);
        System.out.println(i);
    }

    /**
     * 批量删除
     */
    @Test
    public void testDeleteByBatchIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1, 4, 5));
        System.out.println(i);
    }

    /**
     * 简单条件删除
     */
    @Test
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","jack");
        map.put("age",20);
        int i = userMapper.deleteByMap(map);
        System.out.println(i);
    }

    /**
     * 逻辑删除
     */
    @Test
    public void testLogicDelete(){
        int i = userMapper.deleteById(1327545340294864898L);
        System.out.println(i);
    }
}
