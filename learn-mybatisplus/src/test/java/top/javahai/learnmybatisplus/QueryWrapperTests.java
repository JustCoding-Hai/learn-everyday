package top.javahai.learnmybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import net.minidev.json.writer.UpdaterMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.xmlunit.util.Linqy;
import top.javahai.learnmybatisplus.entity.User;
import top.javahai.learnmybatisplus.mapper.UserMapper;

import java.util.List;
import java.util.Map;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 条件构造器
 * @create 2020/11/14 - 19:13
 **/
@SpringBootTest
public class QueryWrapperTests {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询条件测试,ge、gt、le、lt、isNull、isNotNull
     */
    @Test
    public void testSelect(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNotNull("name")
                    .gt("age",18)
                    .isNull("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }

    /**
     * eq、ne.
     * selectOne方法如果数据库中返回的数据大于一个会报错
     */
    @Test
    public void testSelectOne(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name","kobe");
        queryWrapper.ne("id",1327545340160647170L);
        User user = userMapper.selectOne(queryWrapper);
        System.out.println(user);
    }

    /**
     * between、not between
     */
    @Test
    public void testSelectCount(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.between("age",18,25);
        Integer count = userMapper.selectCount(userQueryWrapper);
        System.out.println(count);
    }

    /**
     * like、notLike、likeLeft、likeRight
     */
    @Test
    public void testSelectMaps(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.select("name","age")
        .like("name","k")
        .likeLeft("email",5);
        List<Map<String,Object>> userList = userMapper.selectMaps(userQueryWrapper);
        System.out.println(userList.size());

    }

    /**
     * in、notIn、inSql、notinSql、exists、notExists
     *
     *
     * insql和notinsql适用于子查询的
     */
    @Test
    public void testSelectObjs(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", 1, 2, 3);
        //queryWrapper.inSql("id", "select id from user where id <= 3");
        List<Object> objects = userMapper.selectObjs(queryWrapper);//返回值是Object列表
        objects.forEach(System.out::println);

    }

    /**
     * or、and,没有使用or就默认使用and连接
     */
    @Test
    public void testUpdate1(){
        //修改内容
        User user = new User();
        user.setAge(99);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("name","K")
                         .or()
                         .like("name","hai");
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println(update);
    }

    /**
     *lambda表达式
     */
    @Test
    public void testUpdate2(){
        //修改内容
        User user = new User();
        user.setAge(99);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper.like("name","K")
                .or(i->i.like("name","hai").eq("id",4));
        int update = userMapper.update(user, userUpdateWrapper);
        System.out.println(update);
    }

    /**
     * orderBy、orderByDesc、orderByAsc
     */
    @Test
    public void testSelectListOrderBy(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.orderByAsc("age");
        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.forEach(System.out::println);
    }

    /**
     * set、setSql
     */

    @Test
    public void testUpdateSet() {
        //修改值
        User user = new User();
        user.setAge(60);
        //修改条件
        UpdateWrapper<User> userUpdateWrapper = new UpdateWrapper<>();
        userUpdateWrapper
                .like("name", "h")
                .set("name", "Peter")//除了可以查询还可以使用set设置修改的字段
                .setSql(" email = '123@qq.com'");//可以有子查询
        int result = userMapper.update(user, userUpdateWrapper);
        System.out.println(result);
    }
}
