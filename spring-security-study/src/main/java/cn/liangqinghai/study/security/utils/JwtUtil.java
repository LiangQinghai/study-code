package cn.liangqinghai.study.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.utils</p>
 * <p>File name: JwtUtil</p>
 * <div>
 * <h3>Description: </h3>
 * jwt工具
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 14:53
 */
public class JwtUtil {

    private static final Logger log = LoggerFactory.getLogger(JwtUtil.class);

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String SECRET = "helloWorld";
    private static final Long EXPIRATION = 7200L;

    /**
     * 过期时间
     *
     * @return date
     */
    private static Date genExpirationDate() {

        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);

    }

    /**
     * 生成token
     *
     * @param claims claims
     * @return str token
     */
    private static String genToken(Map<String, Object> claims) {

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(genExpirationDate())
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

    }

    /**
     * token中获取claims
     *
     * @param token token
     * @return claims
     */
    private static Claims getClaimsFromToken(String token) {

        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }

    /**
     * 是否过期
     *
     * @param token token
     * @return true->过期，false->未过期
     */
    private static boolean isTokenExpired(String token) {
        Claims fromToken = getClaimsFromToken(token);
        return fromToken.getExpiration().before(new Date());
    }

    /**
     * 从token中获取用户名
     *
     * @param token token
     * @return string
     */
    public static String getUsernameFromToken(String token) {

        try {
            return getClaimsFromToken(token)
                    .getSubject();
        } catch (Exception e) {
            log.error("token解析用户名失败", e);
            return null;
        }

    }

    /**
     * 检验token有效性
     *
     * @param token token
     * @param userDetails ud
     * @return true->有效
     */
    public static boolean validateToken(String token, UserDetails userDetails) {

        String username = getUsernameFromToken(token);
        return username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token);

    }

    /**
     * 生成token
     *
     * @param userDetails 用户详情
     * @return token
     */
    public static String genToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>(2);
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return genToken(claims);

    }

}
