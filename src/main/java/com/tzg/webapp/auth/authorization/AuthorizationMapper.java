package com.tzg.webapp.auth.authorization;

import com.tzg.service.support.proto.ProtoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorizationMapper extends ProtoMapper< Authorization > {

    void deleteByUserId( Integer userId ) throws Exception;

    void insertList( List< Authorization > list ) throws Exception;

    List< Authorization > selectByUserId( Integer userId ) throws Exception;

}
