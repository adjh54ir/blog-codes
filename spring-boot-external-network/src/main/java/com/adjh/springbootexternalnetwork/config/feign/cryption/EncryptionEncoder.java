package com.adjh.springbootexternalnetwork.config.feign.cryption;

import com.blog.springbootkeycloak.utils.AESEncryption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * ASE256 모듈을 사용하여서 OpenFeign의 인코딩을 수행합니다.
 *
 * @author : leejonghoon
 * @fileName : EncryptionEncoder
 * @since : 2025. 2. 18.
 */
@Slf4j
public class EncryptionEncoder implements Encoder {
    private final AESEncryption aesEncryption = new AESEncryption();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Encoder delegate;

    public EncryptionEncoder(Encoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public void encode(Object object, Type type, RequestTemplate template) {
        try {
            log.debug("인코딩 시점의 응답 값 :: {}", object);
            log.debug("template 시점의 method :: {}", template.method());
            log.debug("template 시점의 url :: {}", template.url());
            log.debug("template 시점의 headers :: {}", template.headers());


            if (requiresEncryption(object)) {
                // 객체를 JSON 문자열로 변환
                String jsonString = objectMapper.writeValueAsString(object);
                // AES 암호화 수행
                String encryptedData = aesEncryption.encrypt(jsonString);

                // Base64 인코딩
                byte[] bytes = encryptedData.getBytes(StandardCharsets.UTF_8);
                String base64EncodedData = Base64.getEncoder().encodeToString(bytes);

                // 인코딩된 데이터를 요청 본문에 설정
                template.body(base64EncodedData);  // String 타입으로 직접 전달
            } else {
                // 암호화가 필요없는 경우 delegate를 통한 기본 인코딩 수행
                delegate.encode(object, type, template);
            }
        } catch (JsonProcessingException e) {
            throw new EncodeException("Failed to convert object to JSON: " + e.getMessage(), e);
        } catch (AESEncryption.AESEncryptionException e) {
            throw new EncodeException("Failed to encrypt data: " + e.getMessage(), e);
        }
    }

    private boolean requiresEncryption(Object object) {
        // 암호화가 필요한 객체인지 판단하는 로직
        // 예: 특정 어노테이션이 있는 경우나 특정 클래스인 경우
        return true; // 실제 구현에서는 조건에 따라 true/false 반환
    }
}