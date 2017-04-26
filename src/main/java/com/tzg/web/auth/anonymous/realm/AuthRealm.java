package com.tzg.web.auth.anonymous.realm;

import com.tzg.web.auth.authorization.Authorization;
import com.tzg.web.auth.permission.Permission;
import com.tzg.web.auth.user.User;
import com.tzg.web.auth.authorization.AuthorizationService;
import com.tzg.web.auth.permission.PermissionService;
import com.tzg.web.auth.resource.ResourceService;
import com.tzg.web.auth.user.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.PasswordService;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

public class AuthRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger( AuthRealm.class );

    @Resource
    private UserService userService;

    @Resource
    private PasswordService passwordService;

    @Resource
    private AuthorizationService authorizationService;

    @Resource
    private PermissionService permissionService;

    @Resource
    private ResourceService resourceService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo( PrincipalCollection principalCollection ) {

        if ( principalCollection == null ) throw new AuthorizationException( "principalCollection is null" );

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        try {

            Map< String, Object > map = new HashMap<>();    //构建查询map

            List< Integer > roleIds = new ArrayList<>();

            // 根据user的id从auth_authorization表中获取role id列表
            {
                User user = ( User ) principalCollection.fromRealm( getName() ).iterator().next();
                map.put( "userId", user.getId() );

                for ( Authorization authorization : authorizationService.selectList( map ) ) {
                    roleIds.add( authorization.getRoleId() );
                }
            }

            List< Integer > resourceIds = new ArrayList<>();

            // 根据role id从auth_permission表中获取resource id列表，需要过滤重复的resource id。
            {
                map.clear();
                map.put( "roleIds", roleIds );

                for ( Permission permission : permissionService.selectList( map ) ) {
                    resourceIds.add( permission.getResId() );
                }

                // 过滤list中的重复resource id值
                Set< Integer > set = new HashSet<>( resourceIds );
                resourceIds.clear();
                resourceIds.addAll( set );
            }

            // 根据resource id从auth_resource表中获取resource记录，并添加到SimpleInfo对象中
            {
                map.clear();
                map.put( "resourceIds", resourceIds );
                List< com.tzg.web.auth.resource.Resource > resources = resourceService.selectList( map );

                for ( com.tzg.web.auth.resource.Resource resource : resources ) {
                    if ( StringUtils.isBlank( resource.getUri() ) ) continue;
                    info.addStringPermission( resource.getUri() );
                }
            }

        } catch ( Exception e ) {
            logger.error( "authorization fail. reason is {}", e );
        }

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo( AuthenticationToken token ) {

        UsernamePasswordToken authToken = ( UsernamePasswordToken ) token;

        User user;

        try {
            user = userService.selectByName( authToken.getUsername() );
        } catch ( Exception e ) {
            logger.error( "query user error. reason is: {}", e );
            throw new AuthenticationException( "user not exist" );
        }

        String plainPwd   = String.valueOf( authToken.getPassword() );
        String encryptPwd = user.getPassword();

        if ( !passwordService.passwordsMatch( plainPwd, encryptPwd ) ) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo( user, authToken.getPassword(), getName() );

    }

}
