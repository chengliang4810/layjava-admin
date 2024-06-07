package com.layjava.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.layjava.common.mybatis.core.entity.BaseEntity;
import java.time.LocalDateTime;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * 用户信息表
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 用户类型（sys_user系统用户）
     */
    private String userType;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 用户性别（0男 1女 2未知）
     */
    private String gender;

    /**
     * 头像地址
     */
    private Long avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;

    /**
     * 备注
     */
    private String remark;
}
