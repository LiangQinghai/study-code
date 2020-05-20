package cn.liangqinghai.study.mbp.properties;

import lombok.Data;

/**
 * @author LiangQinghai
 * @Title RemenberMeCookie
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 17:58
 */
@Data
public class RememberMeCookie {

    private String domain;

    private String path;

    private boolean httpOnly;

    private int maxAge;

}
