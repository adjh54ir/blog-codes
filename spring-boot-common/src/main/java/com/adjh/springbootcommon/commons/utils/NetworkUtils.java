package com.adjh.springbootcommon.commons.utils;

import jakarta.servlet.http.HttpServletRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * 네트워크로 사용되는 유틸
 *
 * @author : jonghoon
 * @fileName : NetworkUtils
 * @since : 3/9/24
 */
@RequiredArgsConstructor
public class NetworkUtils {


    /**
     * Client IP 값을 반환 받는 함수
     *
     * @return {String} ClientIP
     */
    public static String getClientIp() {
        String clientIp = "";
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        clientIp = request.getHeader("X-Forwarded-For");

        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("WL-Proxy-Client-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_CLIENT_IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("X-Real-IP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("X-RealIP");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getHeader("REMOTE_ADDR");
        }
        if (clientIp == null || clientIp.length() == 0 || "unknown".equalsIgnoreCase(clientIp)) {
            clientIp = request.getRemoteAddr();
        }
        System.out.println("Client IP ::" + clientIp);

        return clientIp;
    }

    /**
     * Public IP / External IP 값을 반환 받는 함수
     *
     * @return {String} publicIp
     */
    public static String getPublicIp() {
        String publicIp = "";
        try {
            URL url = new URL("http://checkip.amazonaws.com");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            publicIp = in.readLine();
            System.out.println("External IP Address: " + publicIp);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return publicIp;

    }


    /**
     * Private IP / Internal IP 값을 반환 받는 함수
     *
     * @return {String} privateIp
     */
    public static List<String> getPrivateIp() {
        List<String> privateIpList = new ArrayList<>();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    if (inetAddress.isSiteLocalAddress()) {
                        privateIpList.add(inetAddress.getHostAddress());
                        System.out.println("Internal IP 주소: " + inetAddress.getHostAddress());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return privateIpList;
    }


    /**
     * Host IP 값을 반환 받는 함수
     *
     * @return {String} hostIp
     */
    public static String getHostIp() {
        String localhostIp = "";
        try {
            localhostIp = InetAddress.getLocalHost().getHostAddress();
            System.out.println("Host IP Address: " + localhostIp);
        } catch (UnknownHostException e) {
            System.out.println("Exception :: " + e.getMessage());
        }
        return localhostIp;
    }

}
