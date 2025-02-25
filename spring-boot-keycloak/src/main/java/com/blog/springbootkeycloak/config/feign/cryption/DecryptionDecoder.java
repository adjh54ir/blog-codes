package com.blog.springbootkeycloak.config.feign.cryption;

import com.blog.springbootkeycloak.utils.AESEncryption;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import feign.FeignException;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * AES256 모듈을 사용하여서 OpenFeign의 디코딩을 수행합니다.
 *
 * @author : leejonghoon
 * @fileName : DecryptionDecoder
 * @since : 2025. 2. 18.
 */
@Slf4j
public class DecryptionDecoder implements Decoder {
    private final AESEncryption aesEncryption = new AESEncryption();
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Decoder delegate;

    public DecryptionDecoder(Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        log.debug("디코딩 시점의 응답 값 :: {}", response);

        // 먼저 delegate를 통해 기본 디코딩 수행
        Object decodedObject = delegate.decode(response, type);

        try {
            // 디코딩된 객체를 JSON 문자열로 변환
            String jsonString = objectMapper.writeValueAsString(decodedObject);
            // Base64 디코딩
            byte[] decodedBytes = Base64.getDecoder().decode(jsonString);
            String encryptedData = new String(decodedBytes, StandardCharsets.UTF_8);
            // AES 복호화 수행
            String decryptedData = aesEncryption.decrypt(encryptedData);

            // 복호화된 데이터를 최종 객체로 변환
            return objectMapper.readValue(decryptedData, TypeFactory.defaultInstance().constructType(type));
        } catch (JsonProcessingException e) {
            log.error("JSON processing failed: {}", e.getMessage());
            throw new FeignException.FeignClientException(
                    response.status(),
                    "Failed to process JSON: " + e.getMessage(),
                    response.request(),
                    null,
                    null
            );
        } catch (AESEncryption.AESEncryptionException e) {
            log.error("Decryption failed: {}", e.getMessage());
            throw new FeignException.FeignClientException(
                    response.status(),
                    "Failed to decrypt data: " + e.getMessage(),
                    response.request(),
                    null,
                    null
            );
        }
    }
}