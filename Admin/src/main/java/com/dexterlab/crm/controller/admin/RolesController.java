package com.dexterlab.crm.controller.admin;


import com.dexterlab.crm.core.annotation.ControllerAnnotation;
import com.dexterlab.crm.domain.bean.RoleBean;
import com.dexterlab.crm.service.RolesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author xiaohu
 * @since 2018-09-19
 */
@RestController
@RequestMapping("/api/admin/roles")
public class RolesController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RolesService rolesService;

    public RolesController(RolesService rolesService) {
        this.rolesService = rolesService;
    }

    @ControllerAnnotation(permissionCode = "roles:add", sort = 1)
    @PostMapping
    public void role(@RequestBody RoleBean role){
        rolesService.add(role);
    }

    @ControllerAnnotation(permissionCode = "roles:test", sort = 2)
    @PostMapping("testmethod")
    public void test(@RequestBody RoleBean role){
        rolesService.add(role);
    }
}

