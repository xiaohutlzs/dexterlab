package com.dexterlab.crm.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dexterlab.crm.domain.entity.Permission;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-21
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    public List<Permission> selectListByAccount(Long roleId);
}
