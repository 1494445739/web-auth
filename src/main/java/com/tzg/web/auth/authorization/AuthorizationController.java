package com.tzg.web.auth.authorization;

import com.tzg.service.support.json.JsonResp;
import com.tzg.service.support.proto.ProtoController;
import com.tzg.service.support.proto.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping( "/auth/authz" )
public class AuthorizationController extends ProtoController< Authorization > {

    @Resource
    private AuthorizationService authorizationService;

    @Override
    protected ProtoService getService() {
        return authorizationService;
    }

    @RequestMapping( "/authz" )
    @ResponseBody
    public JsonResp authz( Integer userId, String roleIds ) throws Exception {
        authorizationService.authz( userId, roleIds );
        return this.getSuccessJsonResp();
    }

    @RequestMapping( "/get" )
    @ResponseBody
    public JsonResp selectByUserId( Integer userId ) throws Exception {
        List< Authorization > authzList = authorizationService.selectByUserId( userId );
        return this.getSuccessJsonResp( authzList );
    }

}
