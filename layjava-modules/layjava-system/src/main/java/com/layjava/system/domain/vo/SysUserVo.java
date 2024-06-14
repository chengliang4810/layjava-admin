package com.layjava.system.domain.vo;

import com.easy.query.core.annotation.Navigate;
import com.easy.query.core.enums.RelationTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.layjava.system.domain.SysUser;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 用户信息表
 *
 * @author chengliang4810
 * @since 2024-04-24
 */
@Data
@Accessors(chain = true)
@AutoMapper(target = SysUser.class, convertGenerate = false)
public class SysUserVo implements Serializable {

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 租户编号
     */
    private String tenantId;

    /**
     * 部门ID
     */
    private Long deptId;

    /**
     * 用户账号
     */
    private String account;

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
     * 密码
     */
    @JsonIgnore
    private String password;

    /**
     * 帐号状态（0正常 1停用）
     */
    private String status;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

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

    /**
     * 部门对象
     */
    @Navigate(value = RelationTypeEnum.OneToOne)
    private SysDeptVo dept;

    /**
     * 角色对象
     */
    @Navigate(value = RelationTypeEnum.ManyToMany)
    private List<SysRoleVo> roles;

    /**
     * 角色组
     */
//    @NavigateFlat(value = RelationMappingTypeEnum.ToMany,mappingPath = {
//            SysUser.Fields.roles,
//            SysRole.Fields.roleId
//    })
    private List<Long> roleIds;

    /**
     * 岗位组
     */
    private Long[] postIds;

}
