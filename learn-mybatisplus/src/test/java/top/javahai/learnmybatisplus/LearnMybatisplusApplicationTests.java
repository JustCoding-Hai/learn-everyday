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
        Page<User> page = new Page<>(1, 5);
        IPage<Map<String, Object>> userPage = userMapper.selectMapsPage(page,queryWrapper);
        List<Map<String, Object>> records = userPage.getRecords();
        records.forEach(System.out::println);

    }


}
