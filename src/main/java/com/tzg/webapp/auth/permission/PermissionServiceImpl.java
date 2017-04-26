package com.tzg.webapp.auth.permission;

import com.tzg.service.support.proto.ProtoMapper;
import com.tzg.service.support.proto.ProtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl extends ProtoServiceImpl< Permission > implements PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    protected ProtoMapper< Permission > getMapper() {
        return permissionMapper;
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void authz( Integer roleId, String resourceIds ) throws Exception {

        permissionMapper.deleteByRoleId( roleId );

        List< Permission > permissionList = new ArrayList<>();
        String[]           resourceArr    = resourceIds.split( "-" );
        for ( int i = 0; i < resourceArr.length; i++ ) {
            Permission permission = new Permission();
            permission.setRoleId( roleId );
            permission.setResId( Integer.valueOf( resourceArr[ i ] ) );

            permissionList.add( permission );
        }

        permissionMapper.insertList( permissionList );

    }

    @Override
    public List< Permission > selectByRoleId( Integer roleId ) throws Exception {
        return permissionMapper.selectByRoleId( roleId );
    }
}
