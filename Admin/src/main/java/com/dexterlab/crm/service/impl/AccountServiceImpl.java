package com.dexterlab.crm.service.impl;

import com.dexterlab.crm.domain.entity.Account;
import com.dexterlab.crm.dao.AccountMapper;
import com.dexterlab.crm.service.AccountService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

}
