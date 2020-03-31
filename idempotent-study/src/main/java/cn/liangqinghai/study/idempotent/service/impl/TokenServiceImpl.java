package cn.liangqinghai.study.idempotent.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.liangqinghai.study.idempotent.service.ITokenService;
import cn.liangqinghai.study.idempotent.util.JedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author LiangQinghai
 * @Title TokenServiceImpl
 * @ProjectName study-code
 * @Description
 * @date 3/30/2020 7:47 PM
 */
@Service
public class TokenServiceImpl implements ITokenService {

    @Autowired
    private JedisUtil jedisUtil;

    @Override
    public String getToken() {

        String uuid = IdUtil.fastSimpleUUID();

        jedisUtil.set(uuid, uuid);

        return uuid;
    }

    @Override
    public boolean checkToken(HttpServletRequest request) {

        String uuid = request.getHeader("uuid");

        if (StringUtils.isEmpty(uuid)) {
            return false;
        }

        String uuidIdRedis = jedisUtil.get(uuid);

        if (StringUtils.isEmpty(uuidIdRedis)) {
            return false;
        }

        return true;
    }

    @Override
    public void removeToken(String token) {
        jedisUtil.removeKey(token);
    }
}
