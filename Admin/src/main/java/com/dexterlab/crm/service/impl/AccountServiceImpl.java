package com.dexterlab.crm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dexterlab.crm.dao.AccountMapper;
import com.dexterlab.crm.domain.entity.Account;
import com.dexterlab.crm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

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
    @Autowired
    private RedisTemplate<String, Serializable> template;
    @Override
    public Account getAccountCache(Long id) {
        if(!template.hasKey("account::"+id)){
            Account account =  baseMapper.selectById(id);
            template.opsForValue().set("account::"+id, account);
            return account;
        }else{
            Account account = (Account) template.opsForValue().get("account::"+id);//根据key获取缓存中的val
            return account;
        }
    }

    @Override
    public Account getAccount(Long id) {
        return baseMapper.selectById(id);
    }
}
