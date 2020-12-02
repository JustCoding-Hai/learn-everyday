package top.javahai.datasource.mapper;

import top.javahai.datasource.entity.TUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2020-11-21
 */
public interface TUserMapper extends BaseMapper<TUser> {

     int insertUser(TUser user);

}
