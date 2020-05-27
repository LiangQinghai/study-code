package cn.liangqinghai.study.mbp.utils.freemarker;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * @author LiangQinghai
 * @Title FormatTimeHelper
 * @ProjectName study-code
 * @Description
 * @date 2020/5/27 11:59
 */
public class FormatTimeHelper implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {

        if (templateDirectiveBody != null) {
            throw new TemplateModelException("Body should not be null");
        }

        Writer out = environment.getOut();

        TemplateScalarModel scalarModel = (TemplateScalarModel) map.get("unix");
        if (scalarModel == null) {
            return;
        }

        TemplateScalarModel pattern = (TemplateScalarModel) map.get("pattern");
        String p;

        if (pattern == null) {
            p = "yyyy-MM-dd HH:mm:ss";
        } else {
            p = pattern.getAsString();
        }

        long parseLong = Long.parseLong(scalarModel.getAsString());
        out.write(new SimpleDateFormat(p).format(parseLong * 1000));
        templateDirectiveBody.render(out);

    }
}
