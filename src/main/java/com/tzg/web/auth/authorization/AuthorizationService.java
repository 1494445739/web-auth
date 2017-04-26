package com.tzg.web.auth.authorization;

import com.tzg.service.support.proto.ProtoService;

import java.util.List;

public interface AuthorizationService extends ProtoService< Authorization > {

    void authz( Integer userId, String roleIds ) throws Exception;

    List< Authorization > selectByUserId( Integer userId ) throws Exception;

}
