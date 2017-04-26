package com.tzg.webapp.auth.role;

import com.tzg.service.support.proto.ProtoMapper;
import com.tzg.service.support.proto.ProtoServiceImpl;
import com.tzg.webapp.auth.role.Role;
import com.tzg.webapp.auth.role.RoleMapper;
import com.tzg.webapp.auth.role.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleServiceImpl extends ProtoServiceImpl< Role > implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    protected ProtoMapper< Role > getMapper() {
        return roleMapper;
    }
}
