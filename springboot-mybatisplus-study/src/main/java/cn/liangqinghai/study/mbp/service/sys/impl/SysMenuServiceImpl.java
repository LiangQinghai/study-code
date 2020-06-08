package cn.liangqinghai.study.mbp.service.sys.impl;

import cn.liangqinghai.study.mbp.dao.sys.SysMenuDao;
import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.service.sys.SysMenuService;
import cn.liangqinghai.study.mbp.service.sys.SysUserService;
import cn.liangqinghai.study.mbp.utils.Constant;
import cn.liangqinghai.study.mbp.utils.EhcacheUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author LiangQinghai
 * @Title SysMenuServiceImpl
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 20:53
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuDao, SysMenu> implements SysMenuService {

    @Autowired
    private EhcacheUtil ehcacheUtil;

    @Autowired
    private Constant constant;

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysMenu> queryList(Map<String, Object> map) {
        return baseMapper.queryList(map);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SysMenu> getUserMenuList(Long userId) {

        String cacheName = EhcacheUtil.MENU_EHCACHE + userId;

        Object o = ehcacheUtil.get(EhcacheUtil.ADMIN_EHCACHE, cacheName);

        if (o instanceof List) {
            return (List<SysMenu>) o;
        }

        if (userId.equals(constant.adminId)) {

            List<SysMenu> allMenuList = getAllMenuList(null);

            ehcacheUtil.put(EhcacheUtil.ADMIN_EHCACHE, cacheName, allMenuList);

            return allMenuList;

        }
        List<Long> menuIds = sysUserService.queryAllMenuId(userId);
        List<SysMenu> allMenuList = getAllMenuList(menuIds);
        ehcacheUtil.put(EhcacheUtil.ADMIN_EHCACHE, cacheName, allMenuList);
        return allMenuList;
    }

    @Override
    public List<SysMenu> queryListParentId(Long parentId, List<Long> menuList) {

        List<SysMenu> sysMenus = baseMapper.queryListParentId(parentId);

        if (CollectionUtils.isEmpty(menuList)) {
            return sysMenus;
        }

        return sysMenus.stream().filter(s -> menuList.contains(s.getId())).collect(Collectors.toList());
    }

    private List<SysMenu> getAllMenuList(List<Long> menuIdList) {

        List<SysMenu> sysMenus = queryListParentId(0L, menuIdList);

        getMenuTreeList(sysMenus, menuIdList);

        return sysMenus;

    }

    private List<SysMenu> getMenuTreeList(List<SysMenu> menuList, List<Long> menuIdList) {

        List<SysMenu> subMenuList = new ArrayList<>();

        for (SysMenu sysMenu : menuList) {
            if (sysMenu.getType() == Constant.MenuType.CATALOG.getValue()) {
                sysMenu.setList(getMenuTreeList(queryListParentId(sysMenu.getParentId(), menuIdList), menuIdList));
            }
            subMenuList.add(sysMenu);
        }

        return subMenuList;

    }

}
