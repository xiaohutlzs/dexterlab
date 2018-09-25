package com.dexterlab.crm.service;

import com.dexterlab.crm.domain.bean.RoleBean;
import com.dexterlab.crm.domain.entity.Roles;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-19
 */
public interface RolesService extends IService<Roles> {

    public void add(RoleBean role);
}
