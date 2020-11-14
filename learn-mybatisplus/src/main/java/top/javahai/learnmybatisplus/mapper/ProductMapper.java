package top.javahai.learnmybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import top.javahai.learnmybatisplus.entity.Product;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 商品持久层
 * @create 2020/11/12 - 23:53
 **/
@Repository
public interface ProductMapper extends BaseMapper<Product> {
}
