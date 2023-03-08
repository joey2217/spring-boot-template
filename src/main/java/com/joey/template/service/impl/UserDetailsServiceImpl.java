package com.joey.template.service.impl;

import com.joey.template.entity.DO.UserDO;
import com.joey.template.repository.UserRepository;
import com.joey.template.security.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    @Cacheable(cacheNames = "user", key = "#username")
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误!"));
        return new SecurityUser(user);
    }
}

