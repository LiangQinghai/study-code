package cn.liangqinghai.study.java.base;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author LiangQinghai
 * @Title JsonParser
 * @ProjectName study-code
 * @Description
 * @date 2020/3/12 11:54
 */
public class JsonParser {

    public static void main(String[] args) throws IOException {

//        String arr = "{\"name\": {\"firstName\": \"Qinghai\", \"lastName\": \"Liang\"}, \"skills\": [{\"name\": \"java\", \"blood\": \"50%\"}]}";
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        JsonNode jsonNode = mapper.readTree(arr.getBytes());
//
//        JsonNode name = jsonNode.get("name");
//
//        System.out.println(name);
//
//        JsonNode skills = jsonNode.get("skills");
//
//        System.out.println(skills);

        Pattern pattern = Pattern.compile("^(\\w+)\\[([0-9]+)\\]");

        Matcher matcher = pattern.matcher("aqw[12]");

        int x = matcher.groupCount();
        System.out.println(x);

        if (x > 0) {
            System.out.println(matcher.find());
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        }
    }

}
