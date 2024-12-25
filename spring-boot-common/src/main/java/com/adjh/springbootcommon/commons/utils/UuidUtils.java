package com.adjh.springbootcommon.commons.utils;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;

/**
 * UUID를 버전별로 생성하여 사용하는 유틸
 *
 * @author : jonghoon
 * @fileName : UuidUtils
 * @Method @since : 6/4/24
 * - generateType1UUID()    : UUID v1을 생성하여 반환합니다.(MAC Address, TimeStamp 조합)
 * - generateType3UUID()    : UUID v3를 생성합니다.
 * - generateType4UUID()    : UUID v4를 생성합니다.
 * - generateType5UUID()    : UUID v5를 사용하여 UUID를 생성합니다.
 * - makeUUID()             : UUID v4를 활용한 UUID 반환 함수
 */
@RequiredArgsConstructor
public class UuidUtils {

    /**
     * [공통함수] UUID v1을 생성하여 반환합니다.(MAC Address, TimeStamp 조합)
     *
     * @return
     */
    public static UUID generateType1UUID() {
        long most64SigBits = get64MostSignificantBitsForVersion1();
        long least64SigBits = get64LeastSignificantBitsForVersion1();
        return new UUID(most64SigBits, least64SigBits); // 62dd98f0-bd8e-11ed-93ab-325096b39f47
    }

    /**
     * [MAC Address] MAC 주소 대신에 임의의 48비트 숫자를 생성합니다.(보안 우려로 이를 대체합니다)
     *
     * @return
     */
    private static long get64LeastSignificantBitsForVersion1() {
        Random random = new Random();
        long random63BitLong = random.nextLong() & 0x3FFFFFFFFFFFFFFFL;
        long variant3BitFlag = 0x8000000000000000L;
        return random63BitLong | variant3BitFlag;
    }

    /*
     * [TimeStamp] 타임스템프를 이용하여 64개의 최상위 비트를 생성합니다.
     */
    private static long get64MostSignificantBitsForVersion1() {
        final long currentTimeMillis = System.currentTimeMillis();
        final long time_low = (currentTimeMillis & 0x0000_0000_FFFF_FFFFL) << 32;
        final long time_mid = ((currentTimeMillis >> 32) & 0xFFFF) << 16;
        final long version = 1 << 12;
        final long time_hi = ((currentTimeMillis >> 48) & 0x0FFF);
        return time_low | time_mid | version | time_hi;
    }

    /**
     * [공통함수] UUID v3를 생성합니다.
     *
     * @return
     */
    public static UUID generateType3UUID() {
        String name = "example name";
        UUID uuid3 = UUID.nameUUIDFromBytes(name.getBytes());
        System.out.println("Version 3 UUID: " + uuid3);  // Version 3 UUID: 4dfc6b14-7213-3363-8009-b2
        return uuid3;
    }

    /**
     * [공통함수] UUID v4를 생성합니다.
     *
     * @return
     */
    public static UUID generateType4UUID() {
        // 버전 4 UUID 생성하기
        UUID uuid4 = UUID.randomUUID();
        System.out.println("Version 4 UUID: " + uuid4); // Version 4 UUID: c48b2aef-9d79-44fe-bd97-46fd31361069
        return uuid4;
    }


    /**
     * [공통함수] UUID v5를 사용하여 UUID를 생성합니다.
     *
     * @param name
     * @param namespace
     * @return
     */
    public static UUID generateType5UUID(String name, UUID namespace) {

        UUID uuid = generateType5UUID(name, namespace); // 함수를 호출합니다

        byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
        byte[] namespaceBytes = namespace.toString().getBytes(StandardCharsets.UTF_8);
        byte[] bytesToHash = new byte[nameBytes.length + namespaceBytes.length];

        System.arraycopy(nameBytes, 0, bytesToHash, 0, nameBytes.length);
        System.arraycopy(namespaceBytes, 0, bytesToHash, nameBytes.length, namespaceBytes.length);

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hash = md.digest(bytesToHash);
            hash[6] &= 0x0f;
            hash[6] |= 0x50;
            hash[8] &= 0x3f;
            hash[8] |= 0x80;
            return UUID.nameUUIDFromBytes(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error creating UUID v5", e);
        }
    }


    /**
     * [공통함수] UUID v4를 활용한 UUID 반환 함수
     *
     * @param isHyphen {boolean} 하이픈 사용 여부
     * @return {string} UUID 값 반환
     */
    public static String makeUUID(boolean isHyphen) {
        String result = "";
        if (isHyphen) {
            result = UUID.randomUUID().toString();
        } else {
            result = UUID.randomUUID().toString().replace("-", "");
        }
        return result;
    }

}
