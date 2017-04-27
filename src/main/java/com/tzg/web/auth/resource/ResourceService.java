package com.tzg.web.auth.resource;

import com.tzg.service.support.proto.ProtoService;

public interface ResourceService extends ProtoService< Resource > {

    void deleteNode( Integer id ) throws Exception;

}
