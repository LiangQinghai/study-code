package cn.liangqinghai.study.mbp.service.sys.impl;

import cn.liangqinghai.study.mbp.dao.sys.SysMenuDao;
import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import cn.liangqinghai.study.mbp.service.sys.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author LiangQinghai
 * @Title SysMenuServiceImpl
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 20:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {
    @Override
    public List<SysMenu> queryList(Map<String, Object> map) {
        return baseMapper.queryList(map);
    }
}
