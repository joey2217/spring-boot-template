package com.joey.template.security;

import com.joey.template.exception.JwtInvalidException;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

/**
 * @author Joey
 * @link <a href="https://connect2id.com/products/nimbus-jose-jwt">nimbus-jose-jwt</a>
 */
@Component
@Slf4j
@Data
public class TokenManager {


    @Value("${jwt.expire:86400}")
    private int expire;

    @Value("${jwt.tokenHead:Bearer}")
    private String tokenHead;


    private byte[] secret = new byte[32];

    TokenManager() {
        SecureRandom random = new SecureRandom();
        random.nextBytes(secret);
    }

    public String generateToken(String payload) {
        try {
            // Create HMAC signer
            JWSSigner signer = new MACSigner(secret);
            // Prepare JWT with claims set
            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(payload)
                    .issuer("joey")
                    .expirationTime(new Date(System.currentTimeMillis() + expire))
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            // Apply the HMAC protection
            signedJWT.sign(signer);
            // Serialize to compact form, produces something like
            // eyJhbGciOiJIUzI1NiJ9.SGVsbG8sIHdvcmxkIQ.onO9Ihudz3WkiauDO2Uhyuz0Y18UASXlSc1eS0NkWyA
            String s = signedJWT.serialize();
            return s;
        } catch (JOSEException e) {
            log.error("generateToken error:{}", e.getMessage());
            return null;
        }
    }

    public String verifyToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(secret);
            if (!signedJWT.verify(verifier)) {
                log.error("verify token:{} error", token);
                return null;
            }
            if (!new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())) {
                log.error("token expired", token);
                return null;
            }
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            log.error("verify token:{} error:{}", token, e.getMessage());
            return null;
        }
    }

    public String getTokenFormRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && StringUtils.hasText(header) && header.contains(tokenHead)) {
            return header.split(" ")[1].trim();
        } else {
            return null;
        }
    }

}
