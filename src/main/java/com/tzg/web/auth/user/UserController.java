package com.tzg.web.auth.user;

import com.tzg.service.support.json.JsonResp;
import com.tzg.service.support.proto.ProtoController;
import com.tzg.service.support.proto.ProtoService;
import org.apache.shiro.authc.credential.PasswordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping( "/auth/user" )
public class UserController extends ProtoController< User > {

    private static final Logger logger = LoggerFactory.getLogger( UserController.class );

    @Resource
    private UserService userService;

    @Resource
    private PasswordService passwordService;

    @Override
    protected ProtoService getService() {
        return userService;
    }

    @Override
    @RequestMapping( "/proto/post" )
    @ResponseBody
    public JsonResp post( User user ) throws Exception {

        User persistUser = userService.selectByName( user.getName() );
        if ( persistUser != null && persistUser.getName() != null ) {
            throw new Exception( "user exists." );
        }

        user.setPassword( passwordService.encryptPassword( user.getPassword() ) );
        userService.insert( user );

        return getSuccessJsonResp();

    }

}
