package top.javahai.learn.learnshardingjdbc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.javahai.learn.learnshardingjdbc.Entity.Course;

/**
 * @Author: huangjinhai
 * @Date: 2021\4\26 0026-15:01
 */
@Repository
public interface CourseMapper extends BaseMapper<Course> {
}
