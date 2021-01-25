package cn.liangqinghai.study.mybatis.mapper;

import cn.liangqinghai.study.mybatis.entity.UserInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Mr.Liang
 * @since 2021-01-23
 */
public interface UserInfoMapper extends BaseMapper<UserInfo> {

    @Select("SELECT id,employee_number,display_name,account,email,telephone,valid_date,status,create_user,create_time,update_user,update_time,tenant_id FROM t_permission_user_info " +
            "limit 0, 10")
    List<UserInfo> listPage();

}
