package com.tzg.webapp.auth.permission;

import com.tzg.service.support.proto.ProtoMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper extends ProtoMapper< Permission > {

    List< Permission > selectByRoleId( Integer roleId ) throws Exception;

    void deleteByRoleId( Integer roleId ) throws Exception;

    void insertList( List< Permission > list ) throws Exception;

}
