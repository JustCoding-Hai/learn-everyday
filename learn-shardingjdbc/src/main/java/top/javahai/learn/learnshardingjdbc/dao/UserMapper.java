package top.javahai.learn.learnshardingjdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.javahai.learn.learnshardingjdbc.Entity.User;

/**
 * @Author: huangjinhai
 * @Date: 2021\4\27 0027-10:30
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
}
