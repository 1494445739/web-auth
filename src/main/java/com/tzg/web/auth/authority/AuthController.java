package com.tzg.web.auth.authority;

import com.tzg.service.support.json.JsonResp;
import com.tzg.web.auth.authorization.Authorization;
import com.tzg.web.auth.permission.Permission;
import com.tzg.web.auth.user.User;
import com.tzg.web.auth.authorization.AuthorizationService;
import com.tzg.web.auth.permission.PermissionService;
import com.tzg.web.auth.resource.ResourceService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

@Controller
@RequestMapping( "/auth" )
public class AuthController {

    @Resource
    private ResourceService resourceService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private AuthorizationService authorizationService;

    @RequestMapping( "/index" )
    public ModelAndView index() throws Exception {

        ModelAndView mav = new ModelAndView( "auth/index" );

        Map< String, Object > map = new HashMap<>();
        Set< Integer >        set = new HashSet<>();

        List< Integer > roleIds     = new ArrayList<>();
        List< Integer > resourceIds = new ArrayList<>();

        List< com.tzg.web.auth.resource.Resource > resourceList;
        List< Permission >                            permissionList;
        List< Authorization >                         authorizationList;


        User user = ( User ) SecurityUtils.getSubject().getPrincipal();
        authorizationList = authorizationService.selectByUserId( user.getId() );
        for ( Authorization authorization : authorizationList ) {
            roleIds.add( authorization.getRoleId() );
        }

        map.put( "roleIds", roleIds );

        permissionList = permissionService.selectList( map );
        for ( Permission permission : permissionList ) {
            resourceIds.add( permission.getResId() );
        }
        set.addAll( resourceIds );
        resourceIds.clear();
        resourceIds.addAll( set );

        map.clear();
        map.put( "resourceIds", resourceIds );

        resourceList = resourceService.selectList( map );
        mav.addObject( "resourceList", resourceList );

        return mav;

    }

    @RequestMapping( "/main" )
    public String main() { return "auth/main"; }

    @RequestMapping( "/login" )
    public String login() {
        return "auth/login";
    }

    @RequestMapping( "/authc" )
    @ResponseBody
    public String authc( String username, String password ) {

        UsernamePasswordToken token = new UsernamePasswordToken( username, password );
        token.setRememberMe( true );

        Subject subject = SecurityUtils.getSubject();
        subject.login( token );

        return "redirect:index";

    }

    @RequestMapping( "/logout" )
    @ResponseBody
    public JsonResp logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        return new JsonResp();

    }

}
