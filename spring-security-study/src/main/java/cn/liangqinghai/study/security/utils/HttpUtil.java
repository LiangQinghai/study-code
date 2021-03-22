package cn.liangqinghai.study.security.utils;

import cn.hutool.json.JSONUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.utils</p>
 * <p>File name: HttpUtil</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 16:11
 */
public class HttpUtil {

    public static void writeJson(HttpServletResponse response,
                                 String jsonResult) throws IOException {

        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println(jsonResult);
        writer.flush();

    }

    public static void writeJson(HttpServletResponse response,
                                 Object jsonResult) throws IOException {

        writeJson(response, JSONUtil.toJsonPrettyStr(jsonResult));
    }

}
