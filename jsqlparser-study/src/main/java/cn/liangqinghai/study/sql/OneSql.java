package cn.liangqinghai.study.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;

/**
 * @author Mr.Liang
 * @date 2020/5/25
 */
public class OneSql {

    public static void main(String[] args) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse("select * from tableOne");
    }

}
