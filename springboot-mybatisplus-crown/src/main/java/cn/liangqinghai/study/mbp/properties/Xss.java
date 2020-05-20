package cn.liangqinghai.study.mbp.properties;

import lombok.Data;

import java.util.List;

/**
 * @author LiangQinghai
 * @Title Xss
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:54
 */
@Data
public class Xss {

    private boolean enable = true;

    private List<String> excludeField;

    private List<String> excludeUrls;

}
