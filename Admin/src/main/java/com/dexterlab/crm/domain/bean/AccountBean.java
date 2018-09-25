package com.dexterlab.crm.domain.bean;

import com.dexterlab.crm.domain.entity.Account;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * 账号form
 */
public class AccountBean {

    @Size(max = 20, message = "太长了")
    @NotEmpty(message = "不能为空")
    private String userName;
    private String passWord;
    private Long roleId;
    private AccountType accountType;

    public Account of(){
        Account account = new Account();
        account.setAccount(userName);
        account.setPassword(new BCryptPasswordEncoder().encode(passWord));
        account.setType(accountType);
        account.setRoleId(roleId);
        return account;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }
}
