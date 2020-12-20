package top.javahai.datasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.datasource.annotation.SpecifyDataSource;
import top.javahai.datasource.config.DataSourceType;
import top.javahai.datasource.entity.TUser;
import top.javahai.datasource.mapper.TUserMapper;
import top.javahai.datasource.service.ITUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-21
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {

    @Autowired
    TUserMapper userMapper;


    /**
     * 测试mybatis插入的问题
     * //todo 问题没有复现，周一再看看。版本的问题？
     * @param user
     * @return
     */
    @SpecifyDataSource(value = DataSourceType.MYDB)
    public int insertUser(TUser user){
        return userMapper.insertUser(user);
    }

}
