package cn.liangqinghai.study.security.po;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.po</p>
 * <p>File name: Permission</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 15:34
 */
public class PermissionPO {

    private Long id;

    private String name;

    private String value;

    public Long getId() {
        return id;
    }

    public PermissionPO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PermissionPO setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public PermissionPO setValue(String value) {
        this.value = value;
        return this;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
