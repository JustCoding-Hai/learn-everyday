package top.javahai.learnmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.javahai.learnmybatisplus.entity.User;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 用户数据库操作
 * @create 2020/11/9 - 0:03
 **/
@Repository
public interface UserMapper extends BaseMapper<User> {
}
