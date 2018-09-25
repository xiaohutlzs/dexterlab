package com.dexterlab.crm.domain.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p>
 * 角色权限中间表
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-20
 */
@TableName("t_role_permission")
public class RolePermission extends Model<RolePermission> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 角色表id
     */
    @TableField("role_id")
    private Long roleId;
    /**
     * 权限表code
     */
    @TableField("permission_code")
    private String permissionCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
