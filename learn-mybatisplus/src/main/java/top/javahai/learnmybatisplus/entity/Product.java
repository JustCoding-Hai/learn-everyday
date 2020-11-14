package top.javahai.learnmybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @author Hai
 * @program: learn-everyday
 * @description: 商品类
 * @create 2020/11/12 - 23:51
 **/
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    @Version
    private Integer version;

}
