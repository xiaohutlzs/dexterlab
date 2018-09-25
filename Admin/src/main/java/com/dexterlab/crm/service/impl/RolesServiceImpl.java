package com.dexterlab.crm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dexterlab.crm.dao.RolePermissionMapper;
import com.dexterlab.crm.dao.RolesMapper;
import com.dexterlab.crm.domain.bean.RoleBean;
import com.dexterlab.crm.domain.entity.RolePermission;
import com.dexterlab.crm.domain.entity.Roles;
import com.dexterlab.crm.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-19
 */
@Service
public class RolesServiceImpl extends ServiceImpl<RolesMapper, Roles> implements RolesService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    @Transactional
    @Override
    public void add(RoleBean role) {
        Roles roles = new Roles();
        roles.setName(role.getName());
        baseMapper.insert(roles);
        role.getPermissionCodes().forEach(permissionBean -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionCode(permissionBean.getCode());
            rolePermission.setRoleId(roles.getId());
            rolePermissionMapper.insert(rolePermission);
        });
    }
}
