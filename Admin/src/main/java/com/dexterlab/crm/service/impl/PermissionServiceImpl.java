package com.dexterlab.crm.service.impl;

import com.dexterlab.crm.domain.entity.Permission;
import com.dexterlab.crm.dao.PermissionMapper;
import com.dexterlab.crm.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-21
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
