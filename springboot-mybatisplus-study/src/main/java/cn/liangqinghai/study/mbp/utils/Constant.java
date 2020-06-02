package cn.liangqinghai.study.mbp.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author LiangQinghai
 * @Title Constant
 * @ProjectName study-code
 * @Description
 * @date 2020/6/1 14:38
 */
@Component
@PropertySource("classpath:config.properties")
public class Constant {

    /**
     * 文件夹名称
     */
    public static String uploadSavePathFormat = "yyyyMM";

    /**
     * 文件路径
     */
    @Value("${file.UploadPath}")
    public String uploadPath;

    /**
     * 文件存放路径
     */
    @Value("${file.UploadPath}")
    private String fileContextPath;

    @Value("${database.adminId}")
    public Long adminId;

    /**
     * 管理员名称
     */
    @Value("${database.test.admin.name}")
    public String defaultAdminName;

    /**
     * 管理员密码
     */
    @Value("${database.test.admin.pwd}")
    public String defaultAdminPwd;

    public final String loginSessionAttr = "HelloAdmin";

    /**
     * 菜单类型
     */
    public enum MenuType {

        /**
         * 目录
         */
        CATALOG(0),

        /**
         * 菜单
         */
        MENU(1),

        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

    }

    /**
     * 上传文件类型
     */
    public enum UploadType {

        /**
         * 其他类型
         */
        other(-1),

        /**
         * 管理员头像
         */
        adminAvator(0);

        private int value;

        UploadType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
