package cn.liangqinghai.study.sql;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author LiangQinghai
 * @Title OneSqlTest
 * @ProjectName study-code
 * @Description
 * @date 2020/5/26 19:28
 */
public class OneSqlTest {

    @Test
    public void select() throws JSQLParserException {

        Statement statement = CCJSqlParserUtil.parse("select * from tableOne");

        if (statement instanceof Select) {

            Select select = (Select) statement;

            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();

            List<String> tableList = tablesNamesFinder.getTableList(select);

            System.out.println(Arrays.toString(tableList.toArray()));

//            AddAliasesVisitor addAliasesVisitor = new AddAliasesVisitor();
//
//            select.getSelectBody().accept(addAliasesVisitor);

            System.out.println(select.toString());

        }


    }

}
