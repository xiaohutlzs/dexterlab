package com.dexterlab.crm.controller.admin;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dexterlab.crm.core.annotation.ControllerAnnotation;
import com.dexterlab.crm.domain.PageQuery;
import com.dexterlab.crm.domain.bean.AccountBean;
import com.dexterlab.crm.domain.entity.Account;
import com.dexterlab.crm.service.AccountService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
@RestController
@RequestMapping("/api/admin/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ControllerAnnotation(permissionCode = "ACCOUNT:ALL", sort = 1)
    @GetMapping
    public List<Account> findAll(Authentication auth){
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

    @GetMapping("new")
    public List<Account> findAll1(){
        return accountService.getAll().stream().filter(account -> account.getId()>100 && account.getId()<200).collect(Collectors.toList());
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

    @PostMapping
    public void findAccount(@Valid @RequestBody AccountBean account){
        accountService.insert(account.of());
    }
}

