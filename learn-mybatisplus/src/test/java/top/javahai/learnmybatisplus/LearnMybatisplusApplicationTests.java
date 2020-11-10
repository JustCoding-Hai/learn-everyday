package top.javahai.learnmybatisplus;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.learnmybatisplus.entity.User;
import top.javahai.learnmybatisplus.mapper.UserMapper;

import java.util.List;

@SpringBootTest
class LearnMybatisplusApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    /**
     * 插入操作
     */
    @Test
    public void insert(){
        User user = new User();
        user.setName("kobe");
        user.setAge(18);
        user.setEmail("123@qq.com");
        userMapper.insert(user);
    }
    /**
     * 自动填充使用
     */
    @Test
    public void autoInsert(){
        User user = new User();
        user.setId(1L);
        user.setName("linlin");
        userMapper.updateById(user);
        User user2 = new User();
        user2.setName("super_hai");
        user2.setEmail("1234@qq.com");
        user2.setAge(18);
        userMapper.insert(user2);
    }
    /**
     * 测试乐观锁实现
     */
    @Test
    public void testOptimisticLocker(){
        //查询
        User user = userMapper.selectById(1326185650508038145L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }

}
