package com.joey.template.service.impl;

import com.joey.template.entity.DO.UserDO;
import com.joey.template.security.SecurityUser;
import com.joey.template.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * userinfo
     * @return
     */
    @Override
    public UserDO getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser)authentication.getDetails();
        return securityUser.getUser();
    }
}
