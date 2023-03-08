package com.joey.template.service.impl;

import com.joey.template.entity.DO.UserDO;
import com.joey.template.entity.enums.RoleEnum;
import com.joey.template.entity.param.LoginParam;
import com.joey.template.entity.param.SignupParam;
import com.joey.template.exception.ConflictException;
import com.joey.template.exception.LoginFailException;
import com.joey.template.repository.UserRepository;
import com.joey.template.security.SecurityUser;
import com.joey.template.security.TokenManager;
import com.joey.template.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Joey
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    private final AuthenticationManager authenticationManager;
    private final TokenManager tokenManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginServiceImpl(AuthenticationManager authenticationManager, TokenManager tokenManager, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.tokenManager = tokenManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    /**
     * 登录
     */
    @Override
    public String login(LoginParam loginParam) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginParam.getUsername(),
                    loginParam.getPassword()
            );
            Authentication authenticate = authenticationManager.authenticate(
                    authenticationToken
            );
            log.info("Authenticate:{}", authenticate);
            if (authenticate.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authenticate);
                SecurityUser user = (SecurityUser) authenticate.getPrincipal();
                String token = tokenManager.generateToken(user.getUsername());
                log.info("login generateToken:{}", token);
                return token;
            } else {
                log.error("Login Fail:loginRequest:{}", loginParam);
                throw new LoginFailException("账号或密码错误！", loginParam);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Login Fail:loginRequest:{}", loginParam.toString());
            throw new LoginFailException("账号或密码错误！", loginParam, e);
        }
    }

    /**
     * 注册
     */
    @Override
    public Long signup(SignupParam signupParam) {
        UserDO existedUser = new UserDO();
        existedUser.setUsername(signupParam.getUsername());
        boolean exists = userRepository.exists(Example.of(existedUser));
        if (exists) {
            throw new ConflictException(existedUser.getUsername() + "用户已经存在!");
        }
        String encodedPassword = passwordEncoder.encode(signupParam.getPassword());
        String nickname = signupParam.getNickname();
        if (nickname == null) {
            nickname = signupParam.getUsername();
        }
        UserDO userDO = UserDO.builder()
                .username(signupParam.getUsername())
                .password(encodedPassword)
                .nickname(nickname)
                .role(RoleEnum.ROLE_USER)
                .build();
        UserDO save = userRepository.save(userDO);
        return save.getId();
    }
}
