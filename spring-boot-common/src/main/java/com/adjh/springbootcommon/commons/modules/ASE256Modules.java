package com.adjh.springbootcommon.commons.modules;

import lombok.RequiredArgsConstructor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * ASE256 암호화/복호화
 *
 * @author : leejonghoon
 * @fileName : ASE256Modules
 * @since : 2025. 2. 4.
 */
@RequiredArgsConstructor
public class ASE256Modules {
    //알고리즘
    public static String algorithms = "AES/CBC/PKCS5Padding";

    public static String encrypt_AES(String ID, String AESKey) {
        try {
            String result; // 암호화 결과 값을 담을 변수

            String AESIv = AESKey.substring(0, 16);

            // 암호화/복호화 기능이 포함된 객체 생성
            Cipher cipher = Cipher.getInstance(algorithms);

            // 키로 비밀키 생성
            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            // iv 로 spec 생성
            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());
            // 매번 다른 IV를 생성하면 같은 평문이라도 다른 암호문을 생성할 수 있다.
            // 또한 IV는 암호를 복호화할 사람에게 미리 제공되어야 하고 키와 달리 공개되어도 상관없다

            // 암호화 적용
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParamSpec);

            // 암호화 실행
            byte[] encrypted = cipher.doFinal(ID.getBytes(StandardCharsets.UTF_8)); // ID 암호화(인코딩 설정)
            result = Base64.getEncoder().encodeToString(encrypted); // 암호화 인코딩 후 저장

            return result;
        } catch (Exception e) {
            System.out.println("암호화 중 오류 발생하였습니다. ");
            e.printStackTrace();
        }

        return "";
    }

    // AES 복호화
    public static String decrypt_AES(String ID, String AESKey) {
        try {

            // 암호화/복호화 기능이 포함된 객체 생성
            Cipher cipher = Cipher.getInstance(algorithms);

            String AESIv = AESKey.substring(0, 16);

            // 키로 비밀키 생성
            SecretKeySpec keySpec = new SecretKeySpec(AESKey.getBytes(), "AES");

            // iv 로 spec 생성
            IvParameterSpec ivParamSpec = new IvParameterSpec(AESIv.getBytes());

            // 매번 다른 IV를 생성하면 같은 평문이라도 다른 암호문을 생성할 수 있다.
            // 또한 IV는 암호를 복호화할 사람에게 미리 제공되어야 하고 키와 달리 공개되어도 상관없다
            // 암호화 적용
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParamSpec);

            //암호 해석
            byte[] decodedBytes = Base64.getDecoder().decode(ID);
            byte[] decrypted = cipher.doFinal(decodedBytes);

            String rtn = new String(decrypted, StandardCharsets.UTF_8);

            return rtn;
        } catch (Exception e) {
            System.out.println("복호화 중 오류 발생하였습니다. ");
        }

        return "";
    }

}
