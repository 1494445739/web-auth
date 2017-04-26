package com.tzg.webapp.auth.anonymous.filter;

import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
        filterName = "shiroFilter",
        urlPatterns = "/*",
        initParams = { @WebInitParam( name = "targetFilterLifecycle", value = "true" ) }
)
public class ShiroFilterWrapper extends DelegatingFilterProxy { }
