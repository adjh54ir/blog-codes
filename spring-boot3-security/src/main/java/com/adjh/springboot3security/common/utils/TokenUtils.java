package com.adjh.springboot3security.common.utils;

import com.adjh.springboot3security.model.dto.UserDto;
import com.adjh.springboot3security.model.dto.ValidTokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT의 구성요소를 생성하고 최종적으로 JWT를 생성하여 유효성을 체크하는 유틸입니다.
 *
 * @author lee
 * @fileName TokenUtils
 * @since : 10/1/24
 */
@Log4j2
@Component
public class TokenUtils {

    private static SecretKey JWT_SECRET_KEY;

    /**
     * JWT_SECRET_KEY 변수값에 환경 변수에서 불러온 SECRET_KEY를 주입합니다.
     *
     * @param jwtSecretKey
     */
    public TokenUtils(@Value("${jwt.secret}") String jwtSecretKey) {
        TokenUtils.JWT_SECRET_KEY = Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * '토큰의 만료기간'을 지정하는 메서드
     *
     * @return {Date} Calendar
     */
    private static Date createExpiredDate() {
        Calendar c = Calendar.getInstance();
//        c.add(Calendar.SECOND, 3);        // 10초
        c.add(Calendar.HOUR, 1);             // 1시간
        // c.add(Calendar.HOUR, 8);             // 8시간
        // c.add(Calendar.DATE, 1);             // 1일
        return c.getTime();
    }

    /**
     * JWT의 '헤더' 값을 생성해주는 메서드
     *
     * @return HashMap<String, Object>
     */
    private static Map<String, Object> createHeader() {
        return Jwts.header()
                .add("typ", "JWT")
                .add("alg", "HS256")
                .add("regDate", System.currentTimeMillis()).build();
    }

    /**
     * '사용자 정보' 기반으로 'Claims' 생성하는 메서드
     *
     * @param userDto 사용자 정보
     * @return Map<String, Object>
     */
    private static Map<String, Object> createClaims(UserDto userDto) {
        // 공개 클레임에 사용자의 이름과 이메일을 설정하여 정보를 조회할 수 있다.
        Map<String, Object> claims = new HashMap<>();

        log.info("userId :" + userDto.getUserId());
        log.info("userNm :" + userDto.getUserNm());

        claims.put("userId", userDto.getUserId());
        claims.put("userNm", userDto.getUserNm());
        return claims;
    }

    /**
     * 토큰을 기반으로 유효한 토큰인지 여부를 반환해주는 메서드
     * - Claim 내에서 사용자 정보를 추출합니다.
     *
     * @param token String  : 토큰
     * @return boolean      : 유효한지 여부 반환
     */
    public static boolean isValidToken(String token) {
        try {
            Claims claims = getTokenToClaims(token);
            log.info("expireTime :" + claims.getExpiration());
            log.info("userId :" + claims.get("userId"));
            log.info("userNm :" + claims.get("userNm"));
            return true;
        } catch (ExpiredJwtException exception) {
            log.debug("token expired " + token);
            log.error("Token Expired" + exception);
            return false;
        } catch (JwtException exception) {
            log.debug("token expired " + token);
            log.error("Token Tampered" + exception);
            return false;
        } catch (NullPointerException exception) {
            log.debug("token expired " + token);
            log.error("Token is null" + exception);
            return false;
        }
    }


    /**
     * 사용자 정보를 기반으로 토큰을 생성하여 반환 해주는 메서드
     *
     * @param userDto UserDto : 사용자 정보
     * @return String : 토큰
     */
    public static String generateJwt(UserDto userDto) {
        log.debug("생성된 JWT Secret Key: " + JWT_SECRET_KEY);
        // 사용자 시퀀스를 기준으로 JWT 토큰을 발급하여 반환해줍니다.
        JwtBuilder builder = Jwts.builder()
                .setHeader(createHeader())                              // Header 구성
                .claims(createClaims(userDto))                          // Payload - Claims 구성
                .subject(String.valueOf(userDto.getUserSq()))           // Payload - Subject 구성
                .signWith(JWT_SECRET_KEY)                               // Signature 구성
                .expiration(createExpiredDate());                       // Expired Date 구성
        return builder.compact();
    }


    /**
     * Refresh Token으로 기간을 14일로 지정합니다.
     *
     * @return
     */
    private static Date createRefreshTokenExpiredDate() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 14);
        return c.getTime();
    }

    public static String generateRefreshToken(UserDto userDto) {

        log.debug("JWT Secret Key: " + JWT_SECRET_KEY);
        return Jwts.builder()
                .setHeader(createHeader())
                .claims(createClaims(userDto))
                .subject(String.valueOf(userDto.getUserSq()))
                .signWith(JWT_SECRET_KEY)
                .expiration(createRefreshTokenExpiredDate())
                .compact();
    }


    /**
     * 'Header' 내에서 'Token' 정보를 반환하는 메서드
     *
     * @param header 헤더
     * @return String
     */
    public static String getHeaderToToken(String header) {
        return header.split(" ")[1];
    }


    /**
     * 'JWT' 내에서 'Claims' 정보를 반환하는 메서드
     *
     * @param token : 토큰
     * @return Claims : Claims
     */

    private static Claims getTokenToClaims(String token) {
        System.out.println("확인111  : " + token);
        System.out.println("확인222  : " + JWT_SECRET_KEY);
        return Jwts.parser()
                .verifyWith(JWT_SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /**
     * 'Claims' 내에서 '사용자 아이디'를 반환하는 메서드
     *
     * @param token : 토큰
     * @return String : 사용자 아이디
     */
    public static String getClaimsToUserId(String token) {
        Claims claims = getTokenToClaims(token);
        return claims.get("userId").toString();
    }
}

