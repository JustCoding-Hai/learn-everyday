package top.javahai.datasource.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import top.javahai.datasource.entity.Admin;
import top.javahai.datasource.entity.AdminInquiryDO;
import top.javahai.datasource.mapper.AdminMapper;
import top.javahai.datasource.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    AdminMapper adminMapper;

    public Admin selectAdminById(int id){
        return this.baseMapper.selectById(id);
    }

    /**
     * 测试：
     * 如果resultMap指定的返回值与service的方法指定的返回值不同类型时，如果不在service层做处理直接返回，则直接返回
     * resultMap指定的类型，否则会出现转化失败异常。
     * @return
     */
    public List<AdminInquiryDO> selectAll(){
        return adminMapper.selectAll();
    }
}
