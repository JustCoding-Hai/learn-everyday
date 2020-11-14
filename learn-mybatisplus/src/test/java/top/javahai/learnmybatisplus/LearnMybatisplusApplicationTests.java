package top.javahai.learnmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.javahai.learnmybatisplus.entity.Product;
import top.javahai.learnmybatisplus.entity.User;
import top.javahai.learnmybatisplus.mapper.ProductMapper;
import top.javahai.learnmybatisplus.mapper.UserMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    ProductMapper productMapper;
    /**
     * 测试乐观锁实现,模拟冲突实现，并使用乐观锁插件
     */
    @Test
    public void testConcurrentUpdate(){
        //1、小李
        Product p1 = productMapper.selectById(1L);
        System.out.println("小李取出的价格：" + p1.getPrice());
        //2、小王
        Product p2 = productMapper.selectById(1L);
        System.out.println("小王取出的价格：" + p2.getPrice());
        //3、小李将价格加了50元，存入了数据库
        p1.setPrice(p1.getPrice() + 50);
        productMapper.updateById(p1);
        //4、小王将商品减了30元，存入了数据库
        p2.setPrice(p2.getPrice() - 30);
        int result = productMapper.updateById(p2);
        if(result == 0){//更新失败，重试
            //重新获取数据
            p2 = productMapper.selectById(1L);
            //更新
            p2.setPrice(p2.getPrice() - 30);
            productMapper.updateById(p2);
        }
        //最后的结果
        Product p3 = productMapper.selectById(1L);
        System.out.println("最后的结果：" + p3.getPrice());
    }

}
