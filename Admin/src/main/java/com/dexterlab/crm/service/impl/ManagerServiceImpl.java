package com.dexterlab.crm.service.impl;

import com.dexterlab.crm.domain.entity.Manager;
import com.dexterlab.crm.dao.ManagerMapper;
import com.dexterlab.crm.service.ManagerService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理用户表 服务实现类
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

}
