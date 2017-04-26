package com.tzg.webapp.auth.resource;

import com.tzg.service.support.proto.ProtoMapper;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMapper extends ProtoMapper< Resource > {

    void deleteChildrenByPID( long pid ) throws Exception;

}
