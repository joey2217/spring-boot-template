package com.joey.template.service;

import com.joey.template.entity.param.LoginParam;
import com.joey.template.entity.param.SignupParam;

/**
 * @author Joey
 */
public interface LoginService {
    /**
     * 登录
     * @param loginParam
     * @return
     */
    String login(LoginParam loginParam);

    /**
     * 注册
     * @param signupParam
     * @return
     */
    Long signup(SignupParam signupParam);
}