package com.dexterlab.crm.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.dexterlab.crm.domain.entity.Account;

import java.util.List;

/**
 * <p>
 * 账号表 Mapper 接口
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
public interface AccountMapper extends BaseMapper<Account> {
    List<Account> getAll();
}
