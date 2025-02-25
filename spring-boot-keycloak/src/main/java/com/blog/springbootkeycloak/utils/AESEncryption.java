package com.blog.springbootkeycloak.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * ASE256 암호화/복호화
 *
 * @author : leejonghoon
 * @fileName : ASE256Modules
 * @since : 2025. 2. 4.
 */
public class AESEncryption {
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String SECRET_KEY = "12345678901234567890123456789012"; // 32 바이트

    /**
     * ASE256 암호화
     *
     * @param data 암호화할 데이터
     * @return 암호화된 문자열
     * @throws AESEncryptionException 암호화 과정에서 발생하는 예외
     */
    public String encrypt(String data) throws AESEncryptionException {
        try {
            if (data == null) {
                throw new IllegalArgumentException("Data to encrypt cannot be null");
            }

            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES"
            );

            // IV(Initial Vector) 생성
            byte[] iv = new byte[16];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 암호화 수행
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
            byte[] encrypted = cipher.doFinal(data.getBytes(StandardCharsets.UTF_8));

            // IV와 암호화된 데이터를 결합
            byte[] combined = new byte[iv.length + encrypted.length];
            if (combined.length < iv.length + encrypted.length) {
                throw new IllegalStateException("Buffer overflow detected");
            }
            System.arraycopy(iv, 0, combined, 0, iv.length);
            System.arraycopy(encrypted, 0, combined, iv.length, encrypted.length);

            return Base64.getEncoder().encodeToString(combined);
        } catch (Exception e) {
            throw new AESEncryptionException("Encryption failed", e);
        }
    }

    /**
     * ASE 256 복호화
     *
     * @param encryptedData 복호화할 암호화된 문자열
     * @return 복호화된 문자열
     * @throws AESEncryptionException 복호화 과정에서 발생하는 예외
     */
    public String decrypt(String encryptedData) throws AESEncryptionException {
        try {
            if (encryptedData == null) {
                throw new IllegalArgumentException("Encrypted data cannot be null");
            }

            byte[] combined;
            try {
                combined = Base64.getDecoder().decode(encryptedData);
            } catch (IllegalArgumentException e) {
                throw new AESEncryptionException("Invalid Base64 encoded string", e);
            }

            if (combined.length < 16) {
                throw new AESEncryptionException("Invalid encrypted data length");
            }

            // IV 추출
            byte[] iv = new byte[16];
            System.arraycopy(combined, 0, iv, 0, iv.length);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);

            // 암호화된 데이터 추출
            byte[] encrypted = new byte[combined.length - iv.length];
            System.arraycopy(combined, iv.length, encrypted, 0, encrypted.length);

            // 복호화 수행
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            SecretKeySpec keySpec = new SecretKeySpec(
                    SECRET_KEY.getBytes(StandardCharsets.UTF_8), "AES"
            );
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);

            byte[] decrypted = cipher.doFinal(encrypted);
            return new String(decrypted, StandardCharsets.UTF_8);
        } catch (AESEncryptionException e) {
            throw e;
        } catch (Exception e) {
            throw new AESEncryptionException("Decryption failed", e);
        }
    }

    /**
     * AES 암호화/복호화 관련 커스텀 예외
     */
    public static class AESEncryptionException extends Exception {
        public AESEncryptionException(String message) {
            super(message);
        }

        public AESEncryptionException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}