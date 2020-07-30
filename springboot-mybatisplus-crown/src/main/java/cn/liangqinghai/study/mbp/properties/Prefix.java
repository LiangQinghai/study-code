package cn.liangqinghai.study.mbp.properties;


/**
 * @author LiangQinghai
 * @Title Prefix
 * @ProjectName study-code
 * @Description
 * @date 2020/5/20 9:43
 */
public class Prefix {

    private String avatar = "avatar/";

    private String download = "download/";

    private String upload = "upload/";

    public String getAvatar() {
        return avatar;
    }

    public Prefix setAvatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public String getDownload() {
        return download;
    }

    public Prefix setDownload(String download) {
        this.download = download;
        return this;
    }

    public String getUpload() {
        return upload;
    }

    public Prefix setUpload(String upload) {
        this.upload = upload;
        return this;
    }
}
