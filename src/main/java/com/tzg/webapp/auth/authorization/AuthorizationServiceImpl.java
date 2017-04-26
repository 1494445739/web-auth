package com.tzg.webapp.auth.authorization;

import com.tzg.service.support.proto.ProtoMapper;
import com.tzg.service.support.proto.ProtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationServiceImpl extends ProtoServiceImpl< Authorization > implements AuthorizationService {

    @Resource
    private AuthorizationMapper authorizationMapper;

    @Override
    protected ProtoMapper< Authorization > getMapper() {
        return authorizationMapper;
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void authz( Integer userId, String roleIds ) throws Exception {

        authorizationMapper.deleteByUserId( userId );

        List< Authorization > authorizationList = new ArrayList<>();
        String[]              roleIdArr         = roleIds.split( "-" );
        for ( int i = 0; i < roleIdArr.length; i++ ) {
            Authorization authorization = new Authorization();
            authorization.setUserId( userId );
            authorization.setRoleId( Integer.valueOf( roleIdArr[ i ] ) );

            authorizationList.add( authorization );
        }

        authorizationMapper.insertList( authorizationList );
    }

    @Override
    public List< Authorization > selectByUserId( Integer userId ) throws Exception {
        return authorizationMapper.selectByUserId( userId );
    }

}
