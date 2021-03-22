package cn.liangqinghai.study.security.po;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.security.po</p>
 * <p>File name: UserPO</p>
 * <div>
 * <h3>Description: </h3>
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/3/22 15:23
 */
public class UserPO {

    private Long id;

    private String name;

    private String password;

    public Long getId() {
        return id;
    }

    public UserPO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserPO setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserPO setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
