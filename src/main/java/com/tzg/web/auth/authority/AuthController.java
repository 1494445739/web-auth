package com.tzg.web.auth.authority;

import com.tzg.web.auth.authorization.Authorization;
import com.tzg.web.auth.authorization.AuthorizationService;
import com.tzg.web.auth.permission.Permission;
import com.tzg.web.auth.permission.PermissionService;
import com.tzg.web.auth.resource.ResourceService;
import com.tzg.web.auth.user.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping( "/auth" )
public class AuthController {

    private static final String LOGIN_PAGE        = "../../login";     // 登录页面地址
    private static final String INDEX_PAGE        = "../../index";     // 登录后的首页
    private static final String UNAUTHORIZED_PAGE = "../../401";       // 未授权页面

    @Resource
    private ResourceService resourceService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private AuthorizationService authorizationService;

    /**
     * 授权
     */
    @RequestMapping( "/authz" )
    public ModelAndView authz() throws Exception {

        ModelAndView mav = new ModelAndView( "/index.jsp" );

        Map< String, Object > map = new HashMap<>();
        Set< Integer >        set = new HashSet<>();

        List< Integer > roleIds     = new ArrayList<>();
        List< Integer > resourceIds = new ArrayList<>();

        List< com.tzg.web.auth.resource.Resource > resourceList;
        List< Permission >                         permissionList;
        List< Authorization >                      authorizationList;


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

    /**
     * 登录鉴权。因为用了authc过滤器。所以不用实现登录逻辑。这里只需要判断是否登录报异常，如果登录成功就跳到successUrl，如果登录失败就跳转到LOGIN_PAGE页面
     *
     * @param request HttpServletRequest
     * @return LOGIN_PAGE 登录页面
     */
    @RequestMapping( "/authc" )
    public String authc( HttpServletRequest request ) {

        String errClassName = ( String ) request.getAttribute( FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME );
        if ( UnknownAccountException.class.getName().equals( errClassName ) || IncorrectCredentialsException.class.getName().equals( errClassName ) ) {
            request.setAttribute( "error", "用户名/密码错误" );
        } else if ( errClassName != null ) {
            request.setAttribute( "error", "未知错误" );
        }

        // 登录失败后， 重新跳回到login页面，让用户再次登录
        return LOGIN_PAGE;

    }

    /**
     * 用户登录后的首页
     *
     * @return INDEX_PAGE 登录后的首页
     */
    @RequestMapping( "/index" )
    public String index() {
        return INDEX_PAGE;
    }

    /**
     * 用户登出，注销账户
     *
     * @return LOGIN_PAGE 登录页面
     */
    @RequestMapping( "/logout" )
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return LOGIN_PAGE;
    }

    /**
     * 访问未授权资源.
     */
    @RequestMapping( "/401" )
    public String unauthorized() {
        return UNAUTHORIZED_PAGE;
    }

}
