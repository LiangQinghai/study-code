package cn.liangqinghai.study.mbp.controller.sys;

import cn.liangqinghai.study.mbp.model.sys.SysUser;
import cn.liangqinghai.study.mbp.utils.Constant;
import cn.liangqinghai.study.mbp.utils.EhcacheUtil;
import cn.liangqinghai.study.mbp.utils.ShiroUtils;
import com.alibaba.fastjson.JSONObject;
import com.hazelcast.internal.json.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @author LiangQinghai
 * @Title AbstractController
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 16:07
 */
public abstract class AbstractController {

    @Autowired
    protected Constant constant;

    @Autowired
    protected EhcacheUtil ehcacheUtil;

    protected SysUser getAdmin() {
        return ShiroUtils.getAdmin();
    }

    protected Long getAdminId() {
        return ShiroUtils.getUserId();
    }

    protected Long[] toArray(JsonArray jsonArray) {
        Long[] objs = new Long[jsonArray.size()];

        for (int i = 0; i < jsonArray.size(); i++) {
            objs[i] = Long.parseLong(jsonArray.get(i).toString());
        }

        return objs;
    }

    protected <T> Map<String, T> parseObject(String search, String... keyNames) {

        JSONObject jsonObject = JSONObject.parseObject(search);

        if (jsonObject != null && keyNames != null) {
            Map<String, T> map = new HashMap<>();
            for (String keyName : keyNames) {
                map.put(keyName, (T) jsonObject.get(keyName));
            }
            return map;
        }

        return null;
    }

}
