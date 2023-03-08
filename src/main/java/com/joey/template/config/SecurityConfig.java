package com.joey.template.config;

import com.joey.template.filter.TokenAuthenticationFilter;
import com.joey.template.security.AuthenticationEntryPointHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Joey
 */
@Configuration
public class SecurityConfig {
    private final AuthenticationEntryPointHandler authenticationEntryPointHandler;

    private final TokenAuthenticationFilter tokenAuthenticationFilter;

    public SecurityConfig(AuthenticationEntryPointHandler authenticationEntryPointHandler, TokenAuthenticationFilter tokenAuthenticationFilter) {
        this.authenticationEntryPointHandler = authenticationEntryPointHandler;
        this.tokenAuthenticationFilter = tokenAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 由于使用的是JWT，我们这里不需要csrf
        httpSecurity.csrf().disable();
        // 基于token，所以不需要session
        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 允许对于网站静态资源的无授权访问
        httpSecurity.authorizeHttpRequests(
                authorize -> authorize.requestMatchers(HttpMethod.GET,
                                "/",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/*.html",
                                "/favicon.ico",
                                "/*/*.html",
                                "/*/*.css",
                                "/*/*.js",
                                "/swagger-resources/**",
                                "/v3/api-docs"
                        ).permitAll()
                        // 对登录注册要允许匿名访问
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        //跨域请求会先进行一次options请求
                        .requestMatchers(HttpMethod.OPTIONS)
                        .permitAll()
                        .requestMatchers("/role/**", "/user/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
                        .anyRequest()
                        .permitAll()
        );

        httpSecurity.addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity.exceptionHandling().authenticationEntryPoint(authenticationEntryPointHandler);

        return httpSecurity.build();
    }

    /**
     * 密码明文加密方式配置
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 获取AuthenticationManager（认证管理器），登录时认证使用
     *
     * @param authenticationConfiguration
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}