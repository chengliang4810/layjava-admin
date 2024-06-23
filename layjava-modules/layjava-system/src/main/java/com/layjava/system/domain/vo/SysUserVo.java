package com.layjava.system.domain.vo;

import com.layjava.system.domain.SysUser;
import com.mybatisflex.annotation.RelationManyToMany;
import com.mybatisflex.annotation.RelationOneToOne;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldNameConstants;
import org.noear.snack.annotation.ONodeAttr;

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
@FieldNameConstants
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
    @ONodeAttr(ignore = true)
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
    @RelationOneToOne(selfField = Fields.deptId, targetTable = "sys_dept", targetField = "deptId")
    private SysDeptVo dept;

    /**
     * 角色对象
     */
    @RelationManyToMany(
            joinTable = "sys_user_role", // 中间表
            targetTable = "sys_role",
            selfField = "userId", joinSelfColumn = "user_id",
            targetField = "roleId", joinTargetColumn = "rol  ml.k= 999999999999999999999999999999999999999e_id"
    )
    private List<SysRoleVo> roles;

    /**
     * 岗位组
     */
//    @RelationManyToMany(
//            selfField = "userId",
//            targetTable = "sys_post",
//            targetField = "postId",
//            valueField = "postName",
//            joinTable = "sys_user_post",
//            joinSelfColumn = "user_id",
//            joinTargetColumn = "post_id"
//    )
    private List<String> postIds;

}
