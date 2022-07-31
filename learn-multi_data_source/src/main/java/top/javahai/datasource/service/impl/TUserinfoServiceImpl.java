package top.javahai.datasource.service.impl;

import top.javahai.datasource.annotation.SpecifyDataSource;
import top.javahai.datasource.config.DataSourceTypeEnum;
import top.javahai.datasource.entity.TUserinfo;
import top.javahai.datasource.mapper.TUserinfoMapper;
import top.javahai.datasource.service.ITUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ethan
 * @since 2020-11-19
 */
@Service
public class TUserinfoServiceImpl extends ServiceImpl<TUserinfoMapper, TUserinfo> implements ITUserinfoService {

    @SpecifyDataSource(value = DataSourceTypeEnum.BOOK_DB)
    public TUserinfo selectUserById(String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_name",id);
        return this.baseMapper.selectByMap(map).get(0);
    }


    @SpecifyDataSource(value = DataSourceTypeEnum.BOOK_DB)
    public List<TUserinfo> selectAll(){
        return this.list(null);
    }

}
