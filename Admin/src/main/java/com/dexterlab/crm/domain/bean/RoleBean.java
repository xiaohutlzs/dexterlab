package com.dexterlab.crm.domain.bean;

import com.dexterlab.crm.domain.entity.Roles;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class RoleBean {
    private String name;
    private Long parentId;
    private List<PermissionBean> permissionCodes;

    @JsonCreator
    public RoleBean(@JsonProperty("name") String name,
                    @JsonProperty("parentId") Long parentId,
                    @JsonProperty("permissionCodes") List<PermissionBean> permissionCodes) {
        this.name = name;
        this.parentId = parentId;
        this.permissionCodes = permissionCodes;
    }

    public Roles of(){
        Roles roles = new Roles();
        roles.setName(this.name);
        return roles;
    }

    public String getName() {
        return name;
    }

    public Long getParentId() {
        return parentId;
    }

    public List<PermissionBean> getPermissionCodes() {
        return permissionCodes;
    }
}
