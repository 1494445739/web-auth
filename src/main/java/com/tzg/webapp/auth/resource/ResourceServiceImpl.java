package com.tzg.webapp.auth.resource;

import com.tzg.service.support.proto.ProtoMapper;
import com.tzg.service.support.proto.ProtoServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResourceServiceImpl extends ProtoServiceImpl< Resource > implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    @Override
    protected ProtoMapper< Resource > getMapper() {
        return resourceMapper;
    }

    @Override
    @Transactional( rollbackFor = Exception.class )
    public void deleteNode( Integer id ) throws Exception {
        resourceMapper.delete( id );
        resourceMapper.deleteChildrenByPID( id );
    }
}
