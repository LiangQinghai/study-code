//package cn.liangqinghai.study.sql;
//
//import net.sf.jsqlparser.JSQLParserException;
//import net.sf.jsqlparser.parser.CCJSqlParserUtil;
//import net.sf.jsqlparser.statement.Statement;
//import net.sf.jsqlparser.statement.select.Select;
//import net.sf.jsqlparser.util.TablesNamesFinder;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author Mr.Liang
// * @date 2020/5/25
// */
//public class OneSql {
//
//    public static void main(String[] args) throws JSQLParserException {
//        Statement statement = CCJSqlParserUtil.parse("select * from tableOne");
//
//        if (statement instanceof Select) {
//
//            Select select = (Select) statement;
//
//            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
//
//            List<String> tableList = tablesNamesFinder.getTableList(select);
//
//            System.out.println(Arrays.toString(tableList.toArray()));
//
//            System.out.println(select.toString());
//
//        }
//
//    }
//
//}
