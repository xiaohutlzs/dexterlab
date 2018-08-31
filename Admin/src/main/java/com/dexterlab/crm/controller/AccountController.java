package com.dexterlab.crm.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dexterlab.crm.domain.PageQuery;
import com.dexterlab.crm.domain.entity.Account;
import com.dexterlab.crm.service.AccountService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public List<Account> findAll(){
        EntityWrapper ew=new EntityWrapper();
        ew.setEntity(new Account());
//        ew.where("name={0}", "'zhangsan'").and("id=1")
//                .orNew("status={0}", "0").or("status=1")
//                .notLike("nlike", "notvalue")
//                .andNew("new=xx").like("hhh", "ddd")
//                .andNew("pwd=11").isNotNull("n1,n2").isNull("n3")
//                .groupBy("x1").groupBy("x2,x3")
//                .having("x1=11").having("x3=433")
//                .orderBy("dd").orderBy("d1,d2");
        return accountService.selectList(ew);
    }

    @GetMapping("page")
    public Page<Account> findAll(PageQuery page){
        return accountService.selectPage(page.buildPage());
    }

    @GetMapping("{accountId}")
    public Account findAccount(@PathVariable Long accountId){
            return accountService.selectById(accountId);
    }

    @GetMapping("nocache/{accountId}")
    public Account findAccount1(@PathVariable Long accountId){
        return accountService.getAccount(accountId);
    }

    @GetMapping("cache/{accountId}")
    public Account findAccount2(@PathVariable Long accountId){
        return accountService.getAccountCache(accountId);
    }
}

