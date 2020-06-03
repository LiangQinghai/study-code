package cn.liangqinghai.study.mbp.dao.sys;

import cn.liangqinghai.study.mbp.model.sys.SysMenu;
import cn.liangqinghai.study.mbp.utils.dao.BaseDao;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title SysMenuDao
 * @ProjectName study-code
 * @Description
 * @date 2020/6/3 20:42
 */
public interface SysMenuDao extends BaseDao<SysMenu> {

    List<SysMenu> queryListParentId(Long parentId);

    @ResultType(SysMenu.class)
    @Select("<script>" +
            "Select * from SYS_MENU " +
            "WHERE TYPE != 2" +
            "ORDER BY ORDER_NUM ASC" +
            "</script>")
    List<SysMenu> queryNotButtonList();

}
