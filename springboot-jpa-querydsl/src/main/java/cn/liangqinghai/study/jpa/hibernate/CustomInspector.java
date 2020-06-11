package cn.liangqinghai.study.jpa.hibernate;

import org.hibernate.resource.jdbc.spi.StatementInspector;

/**
 * @author LiangQinghai
 * @Title CustomInspector
 * @ProjectName study-code
 * @Description
 * @date 2020/6/11 10:09
 */
public class CustomInspector implements StatementInspector {

    private static final long serialVersionUID = 4370831407645099274L;

    @Override
    public String inspect(String sql) {

        System.out.println(sql);

        return sql;
    }
}
