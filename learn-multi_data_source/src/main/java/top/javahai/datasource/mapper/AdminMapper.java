package top.javahai.datasource.mapper;

import top.javahai.datasource.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.javahai.datasource.entity.AdminInquiryDO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ethan
 * @since 2020-11-19
 */
public interface AdminMapper extends BaseMapper<Admin> {

    List<AdminInquiryDO> selectAll();
}
