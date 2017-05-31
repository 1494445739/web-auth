package com.tzg.web.auth.anonymous.filter;

import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class URLPermFilter extends PermissionsAuthorizationFilter {

    public boolean isAccessAllowed( ServletRequest request, ServletResponse response, Object mappedValue )
            throws IOException {
        return super.isAccessAllowed( request, response, getPerm( request ) );
    }

    /**
     * 根据请求URL产生权限字符串。这里只产生，而对比的事交给Realm
     */
    private Object getPerm( ServletRequest request ) {
        HttpServletRequest req = ( HttpServletRequest ) request;
        return new String[]{ req.getRequestURI() };
    }

}
