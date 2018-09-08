package com.dexterlab.crm.controller;


import com.dexterlab.crm.domain.entity.Manager;
import com.dexterlab.crm.service.ManagerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 管理用户表 前端控制器
 * </p>
 *
 * @author xiaohu
 * @since 2018-08-31
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public List<Manager> getManager(){
//        for(var i=0;i<10000;i++){
//            Manager manager = new Manager();
//            manager.setAge(RandomUtils.nextInt());
////            manager.setDepartment(new String[]{"aaa"});
//            manager.setGender("男");
//            manager.setName("测试"+manager.getAge());
//            managerService.insert(manager);
//        }
        return managerService.getManagerByAge(1);
    }
}

