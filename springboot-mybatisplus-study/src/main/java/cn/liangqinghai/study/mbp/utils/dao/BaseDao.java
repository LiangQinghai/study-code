package cn.liangqinghai.study.mbp.utils.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * @author LiangQinghai
 * @Title BaseDao
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 19:27
 */
public interface BaseDao<T> extends BaseMapper<T> {

    List<T> queryList(Map<String, Object> map);

    int queryTotal(Map<String, Object> map);

    int deleteBatch(Long[] ids);

}
