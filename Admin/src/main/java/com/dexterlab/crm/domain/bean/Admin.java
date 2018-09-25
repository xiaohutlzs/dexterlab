package com.dexterlab.crm.domain.bean;

import com.dexterlab.crm.domain.entity.Account;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Admin {

    @Size(max = 20, message = "太长了")
    @NotEmpty(message = "不能为空")
    private String userName;
    private String passWord;

    public Account of(){
        Account account = new Account();
        account.setAccount(userName);
        account.setPassword(passWord);
        account.setType(AccountType.ADMIN);
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
}
