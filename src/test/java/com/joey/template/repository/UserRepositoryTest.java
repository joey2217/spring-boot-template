package com.joey.template.repository;

import com.joey.template.entity.DO.UserDO;
import com.joey.template.entity.enums.RoleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void saveUserTest() {
        String pass = "admin123456";
        String encodedPassword = passwordEncoder.encode(pass);
        UserDO userDO = UserDO.builder()
                .username("admin")
                .password(encodedPassword)
                .nickname("admin")
                .role(RoleEnum.ROLE_ADMIN)
                .build();
        UserDO save = userRepository.save(userDO);
        log.info(save.toString());
        Assertions.assertTrue(save.getId() != null);
    }

    @Test
    void passwordEncoderTest() {
        String pass = "admin123456";
        String encodedPassword = passwordEncoder.encode(pass);
        log.info(encodedPassword);
        Assertions.assertTrue( passwordEncoder.matches(pass,encodedPassword));
        Assertions.assertTrue(encodedPassword.length() > 0);
    }
}