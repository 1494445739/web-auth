package com.tzg.webapp.auth.anonymous.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AbstractFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter extends AbstractFilter {

    private static final Logger logger = LoggerFactory.getLogger( AuthFilter.class );

    private final static String PAGE_LOGIN = "/auth/login";

    private final static String PAGE_404 = "/src/html/404.html";

    @Override
    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain ) throws IOException, ServletException {

        HttpServletRequest  req = ( HttpServletRequest ) request;
        HttpServletResponse res = ( HttpServletResponse ) response;

        logger.debug( "shiro filter intercept, request uri is : {} ", ( req.getServletPath() ) );

        Subject subject = SecurityUtils.getSubject();

        if ( req.getSession().isNew() || req.getSession( false ) == null || !subject.isAuthenticated() ) {
            res.sendRedirect( req.getContextPath().concat( PAGE_LOGIN ) );
            return;
        }

        if ( !subject.isPermitted( req.getRequestURI() ) ) {
            res.sendRedirect( req.getContextPath().concat( PAGE_404 ) );
        }

        chain.doFilter( req, res );

    }

}