package com.tzg.webapp.auth.user;

import com.tzg.service.support.proto.ProtoMapper;
import com.tzg.service.support.proto.ProtoServiceImpl;
import com.tzg.webapp.auth.user.User;
import com.tzg.webapp.auth.user.UserMapper;
import com.tzg.webapp.auth.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ProtoServiceImpl< User > implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    protected ProtoMapper< User > getMapper() {
        return userMapper;
    }

    public User selectByName( String name ) throws Exception {
        return userMapper.selectByName( name );
    }

}
