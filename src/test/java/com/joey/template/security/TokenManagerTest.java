package com.joey.template.security;

import com.nimbusds.jose.JOSEException;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TokenManagerTest {

    @Autowired
    TokenManager tokenManager;

    @Test
    void generateTokenTest() throws JOSEException {
        String payload = "1";
        String s = tokenManager.generateToken(payload);
        log.info(s);
        Assertions.assertTrue(s.length()>0);
    }

    @Test
    void verifyTokenTest() throws ParseException, JOSEException {
        String payload = "1";
        String s = tokenManager.generateToken(payload);
        log.info(s);
        Assertions.assertTrue(s.length()>0);
        String tokenPayload = tokenManager.verifyToken(s);
        log.info(tokenPayload);
        Assertions.assertTrue(tokenPayload.equals(payload));
    }
}