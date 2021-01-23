package cn.liangqinghai.study.webflux.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * <p>Project name: study-code</p>
 * <p>Package name: cn.liangqinghai.study.webflux.domain</p>
 * <p>File name: UserInfoPo</p>
 * <div>
 * <h3>Description: </h3>
 * TODO
 * </div>
 *
 * @author LiangQinghai
 * @since 2021/1/23 13:09
 */
@Table("t_permission_user_info")
public class UserInfoPo {

    @Id
    private Long id;

    /**
     * 用户编号
     */
    @Column("employee_number")
    private String employeeNumber;

    /**
     * 用户名字
     */
    @Column("display_name")
    private String displayName;

    /**
     * 用户账号
     */
    @Column("account")
    private String account;

    /**
     * 用户邮箱
     */
    @Column("email")
    private String email;

    /**
     * 用户手机号码
     */
    @Column("telephone")
    private String telephone;

    /**
     * 账号有效时间
     */
    @Column("valid_date")
    private LocalDateTime validDate;

    /**
     * 是否冻结
     */
    @Column("status")
    private Integer status;

    /**
     * 创建者
     */
    @Column("create_user")
    private String createUser;

    /**
     * 创建时间
     */
    @Column("create_time")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @Column("update_user")
    private String updateUser;

    /**
     * 更新时间
     */
    @Column("update_time")
    private LocalDateTime updateTime;

    /**
     * 租户id
     */
    @Column("tenant_id")
    private Long tenantId;

    public Long getId() {
        return id;
    }

    public UserInfoPo setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public UserInfoPo setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public UserInfoPo setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public UserInfoPo setAccount(String account) {
        this.account = account;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserInfoPo setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getTelephone() {
        return telephone;
    }

    public UserInfoPo setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public LocalDateTime getValidDate() {
        return validDate;
    }

    public UserInfoPo setValidDate(LocalDateTime validDate) {
        this.validDate = validDate;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public UserInfoPo setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getCreateUser() {
        return createUser;
    }

    public UserInfoPo setCreateUser(String createUser) {
        this.createUser = createUser;
        return this;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public UserInfoPo setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public UserInfoPo setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
        return this;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public UserInfoPo setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public UserInfoPo setTenantId(Long tenantId) {
        this.tenantId = tenantId;
        return this;
    }
}
