package com.tzg.webapp.auth.permission;

import com.tzg.service.support.json.JsonResp;
import com.tzg.service.support.proto.ProtoController;
import com.tzg.service.support.proto.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping( "/auth/permission" )
public class PermissionController extends ProtoController< Permission > {

    @Resource
    private PermissionService permissionService;

    @Override
    protected ProtoService getService() {
        return permissionService;
    }

    @RequestMapping( "/get" )
    @ResponseBody
    public JsonResp selectByRoleId( Integer roleId ) throws Exception {
        List< Permission > permissionList = permissionService.selectByRoleId( roleId );
        return this.getSuccessJsonResp( permissionList );
    }

    @RequestMapping( "/authz" )
    @ResponseBody
    public JsonResp authz( Integer roleId, String resourceIds ) throws Exception {
        permissionService.authz( roleId, resourceIds );
        return this.getSuccessJsonResp();
    }

}
