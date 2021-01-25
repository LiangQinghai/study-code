package cn.liangqinghai.study.mybatis;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.mybatis</p>
 * <p>File name: CodeGenerator</p>
 * <div>
 * <h3>Description: </h3>
 * 代码生成器
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/23 10:11
 */
public class CodeGenerator {

    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

        GlobalConfig config = new GlobalConfig();
        config.setOutputDir(System.getProperty("user.dir") + "/springboot-mybatis" + "/src/main/java");
//        config.setOutputDir("C:\\Users\\WakeData\\Desktop\\test");
        config.setAuthor("Mr.Liang");
        config.setOpen(false);

        autoGenerator.setGlobalConfig(config);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://172.31.3.51:3306/DW_PERMISSION_TEST?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("mysqluser");
        dataSourceConfig.setPassword("mysqluser");

        autoGenerator.setDataSource(dataSourceConfig);

        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("cn.liangqinghai.study.mybatis");
        autoGenerator.setPackageInfo(packageConfig);

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("t_permission_", "T_DW_");
        strategyConfig.setEntityLombokModel(false);
        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);

        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();

    }

}
