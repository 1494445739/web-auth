package com.tzg.web.auth.user;

import com.tzg.service.support.proto.ProtoService;
import com.tzg.web.auth.user.User;

public interface UserService extends ProtoService< User > {

    User selectByName( String name ) throws Exception;

}
