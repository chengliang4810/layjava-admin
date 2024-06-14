package com.layjava.system.domain;

import com.easy.query.core.annotation.*;
import com.easy.query.core.enums.RelationTypeEnum;
import com.easy.query.core.proxy.ProxyEntityAvailable;
import com.layjava.common.dao.core.entity.BaseEntity;
import com.layjava.system.domain.proxy.SysUserProxy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;

import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * 用户信息表
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Data
@EntityProxy
@Table("sys_user")
@EasyAlias("sysUser")
@FieldNameConstants
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SysUser extends BaseEntity implements ProxyEntityAvailable<SysUser , SysUserProxy> {

    /**
     * 用户ID
     */
    @Column(primaryKey = true)
    private Long userId;

    /**
     * 部门ID
     */
    private Long deptId;

    @Navigate(value = RelationTypeEnum.OneToOne, selfProperty = "deptId", targetProperty = "deptId")
    private SysDept dept;

    @Navigate(value = RelationTypeEnum.ManyToMany,
            mappingClass = SysUserRole.class,
            selfMappingProperty = "userId",
            targetMappingProperty = "roleId")
    private List<SysRole> roles;

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
