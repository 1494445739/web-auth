package com.tzg.webapp.auth.role;

import com.tzg.service.support.proto.ProtoController;
import com.tzg.service.support.proto.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping( "/auth/role" )
public class RoleController extends ProtoController< Role > {

    @Resource
    private RoleService roleService;

    @Override
    protected ProtoService getService() {
        return roleService;
    }

}
