package com.joey.template.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.joey.template.entity.DO.UserDO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Joey
 */
public class SecurityUser implements UserDetails {

    private String username;

    @JsonIgnore
    private String password;

    private UserDO user;

    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser() {
    }

    public SecurityUser(UserDO user) {
        this.user = user;
        this.username = user.getUsername();
        this.password = user.getPassword();
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().getName()));
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserDO getUser() {
        return user;
    }
}
