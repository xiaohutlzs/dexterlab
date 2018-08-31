package com.dexterlab.crm.service;

import com.baomidou.mybatisplus.service.IService;
import com.dexterlab.crm.domain.entity.Account;

/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
public interface AccountService extends IService<Account> {
    Account getAccountCache(Long id);

    Account getAccount(Long id);
}
