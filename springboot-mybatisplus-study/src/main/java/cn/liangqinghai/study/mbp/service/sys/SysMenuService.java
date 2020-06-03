package cn.liangqinghai.study.mbp.service.sys;

import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * @author LiangQinghai
 * @Title SysMenuService
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 20:42
 */
public interface SysMenuService extends IService<SysMenu> {

    List<SysMenu> queryList(Map<String, Object> map);

}
