package com.dexterlab.crm.dao;

import com.dexterlab.crm.domain.entity.Manager;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 管理用户表 Mapper 接口
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
public interface ManagerMapper extends BaseMapper<Manager> {

    List<Manager> selectByAge(int age);
}
