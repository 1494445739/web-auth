package com.tzg.webapp.auth.user;

import com.tzg.service.support.proto.ProtoMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends ProtoMapper< User > {

    User selectByName( String name ) throws Exception;

}
