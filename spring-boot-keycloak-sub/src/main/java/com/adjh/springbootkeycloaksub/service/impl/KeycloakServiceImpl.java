package com.adjh.springbootkeycloaksub.service.impl;

import com.adjh.springbootkeycloaksub.service.KeycloakService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.TokenVerifier;
import org.keycloak.common.VerificationException;
import org.keycloak.representations.AccessToken;
import org.springframework.stereotype.Service;

import java.security.PublicKey;

/**
 * Please explain the class!!
 *
 * @author : leejonghoon
 * @fileName : KeycloakServiceImpl
 * @since : 2025. 1. 31.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {


    /**
     * 전달받은 토큰의 유효성을 검증합니다.
     *
     * @param token
     * @return
     */
    @Override
    public boolean isValidToken(String token) {
        private final KeycloakAuthenticationProvider authProvider;
        try {
            // 1. JWT 토큰 파싱
            AccessToken accessToken = TokenVerifier.create(token, AccessToken.class)
                    .parse()
                    .getToken();

            // 2. 토큰 서명 검증
            TokenVerifier.create(token, AccessToken.class)
                    .publicKey(getPublicKey())
                    .verify();

            // 3. 만료 시간 확인
            if (accessToken.isExpired()) {
                return false;
            }

            // 4. 발급자(issuer) 확인
            if (!accessToken.getIssuer().equals(expectedIssuer)) {
                return false;
            }

            // 5. 필요한 권한 확인
            if (!hasRequiredRoles(accessToken)) {
                return false;
            }

            return true;

        } catch (VerificationException e) {
            log.error("Token verification failed", e);
            return false;
        }
        return true;
    }

//    private boolean hasRequiredRoles(AccessToken token) {
//        AccessToken.Access access = token.getRealmAccess();
//        return access != null && access.getRoles().contains("required-role");
//    }
//
//    private PublicKey getPublicKey() {
//        // Keycloak 서버로부터 공개키 조회
//        return keycloak.getPublicKey();
//    }

}
