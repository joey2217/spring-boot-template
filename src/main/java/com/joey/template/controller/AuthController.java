package com.joey.template.controller;

import com.joey.template.entity.param.LoginParam;
import com.joey.template.entity.param.SignupParam;
import com.joey.template.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Joey
 */
@RestController
@RequestMapping("/auth")
@Tag(name = "登录注册接口", description = "登录注册")
public class AuthController {
    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    @Operation(summary = "登录接口")
    public ResponseEntity login(@Valid @RequestBody LoginParam loginParam) {
        String token = loginService.login(loginParam);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    @Operation(summary = "注册接口")
    public ResponseEntity signup(@Valid @RequestBody SignupParam signupParam) {
        Long signupId = loginService.signup(signupParam);
        return ResponseEntity.ok(signupId);
    }
}