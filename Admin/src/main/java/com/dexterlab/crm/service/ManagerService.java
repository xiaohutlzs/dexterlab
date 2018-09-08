package com.dexterlab.crm.service;

import com.dexterlab.crm.domain.entity.Manager;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 管理用户表 服务类
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
public interface ManagerService extends IService<Manager> {

    List<Manager> getManagerByAge(Integer nextInt);
}
