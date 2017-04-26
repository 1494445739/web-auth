package com.tzg.web.auth.resource;

import com.tzg.service.support.json.JsonResp;
import com.tzg.service.support.proto.ProtoController;
import com.tzg.service.support.proto.ProtoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping( "/auth/resource" )
public class ResourceController extends ProtoController< Resource > {

    @javax.annotation.Resource
    private ResourceService resourceService;

    @Override
    protected ProtoService getService() {
        return resourceService;
    }

    @RequestMapping( "/delete/node/{id}" )
    @ResponseBody
    public JsonResp deleteNode( @PathVariable Integer id ) throws Exception {
        resourceService.deleteNode( id );
        return getSuccessJsonResp();
    }

}
