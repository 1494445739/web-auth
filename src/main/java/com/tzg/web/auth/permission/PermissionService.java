package com.tzg.web.auth.permission;

import com.tzg.service.support.proto.ProtoService;

import java.util.List;

public interface PermissionService extends ProtoService< Permission > {

    void authz( Integer roleId, String resourceIds ) throws Exception;

    List< Permission > selectByRoleId( Integer roleId ) throws Exception;

}
