package com.blog.springbootkeycloak.config;

import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OpenFeign Header 설정 환경 파일
 *
 * @author : jonghoon
 * @fileName : FeignClientHeaderConfig
 * @since : 25. 2. 13.
 */
@Slf4j
@Configuration
public class FeignClientHeaderConfig {

    /**
     * 공통 Header를 구성합니다.
     *
     * @return
     */
    @Bean
    public RequestInterceptor requestHeaderInterceptor() {

        log.warn("요청 인터셉터 수행 시점 >>>>>>>>>>>>>>>>>>");
        return requestTemplate -> {
            // Bearer 토큰 추가
            requestTemplate.header("Authorization", "Bearer " + getTempAccessToken());
            // 공통 헤더 추가
            requestTemplate.header("x-api-key", "your-api-key");
            requestTemplate.header("Custom-Header", "common-value");
        };
    }

    /**
     * TODO: 삭제 예정
     *
     * @return
     */
    private String getTempAccessToken() {
        return "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJvcWltNkgyQ1A0M0xvYVp3YUlwaXhRZmNRb3NfcFFQZ0RqbkdubWxKdWpJIn0.eyJleHAiOjE3Mzk0NzQ2NjIsImlhdCI6MTczOTQzODY2MiwianRpIjoiMzQ0OTZiZTAtMjJmZC00MzZmLWE2NTEtMDJlMzYxYzM3OTBkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAxL3JlYWxtcy9kZXYtcmVhbG0iLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImJyb2tlciIsImFjY291bnQiXSwic3ViIjoiMzI4OWUyNzUtODRkMy00MDExLTliNTYtMWUxNTJlZDk0ZmUxIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoic3ByaW5nLWJvb3QtYXBwIiwic2lkIjoiODk2ZDY5ZTgtNWMyOC00MjhiLWJjYWItM2U2YWMzODZkN2E0IiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAvKiIsImh0dHA6Ly9sb2NhbGhvc3Q6MzAwMC8qIl0sInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJvZmZsaW5lX2FjY2VzcyIsImRlZmF1bHQtcm9sZXMtZGV2LXJlYWxtIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJyZWFsbS1tYW5hZ2VtZW50Ijp7InJvbGVzIjpbInZpZXctcmVhbG0iLCJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwicmVhbG0tYWRtaW4iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sInNwcmluZy1ib290LWFwcCI6eyJyb2xlcyI6WyJzcHJpbmctYm9vdC1hcHAtc3ViLXJvbGUiXX0sImJyb2tlciI6eyJyb2xlcyI6WyJyZWFkLXRva2VuIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50Iiwidmlldy1hcHBsaWNhdGlvbnMiLCJ2aWV3LWNvbnNlbnQiLCJ2aWV3LWdyb3VwcyIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwiZGVsZXRlLWFjY291bnQiLCJtYW5hZ2UtY29uc2VudCIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoicHJvZmlsZSBvcGVuaWQgZW1haWwiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJzdWIgYWRtaW4iLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJzdWJhZG1pbiIsImdpdmVuX25hbWUiOiJzdWIiLCJmYW1pbHlfbmFtZSI6ImFkbWluIiwiZW1haWwiOiJzdWJhZG1pbkB0ZXN0LmNvbSJ9.N7OKz1ra6o9ZYrXqENTocNLAcqBi5k0DLomQa6WKNpdl7rVw2XnNPGw4HcQNSle_-CFiThA73ETOcO8PhRRvvAw9hu4pPxbgjKhIEZzsw0jDddLw8LSx8h2c8_yRcygSmAkqajHLbNdtFPK-WoRySEeoxbalHQ9LBrxyCjT2j93Z0zd294lDR5NAJ6ZF6mEnzUwrRoDDnBAX7zH5lkUpDv9E3rxW_ZtuhQC6kpgU7M0NT_iSxfQ5tkvV1plIe1Kpks5YPbhnDadTW9Zy-0rqXyN-nu3D3m_28xQBZqIZVTVgR54InMVJ-PsFxtlJyaYOnsASDDMCZMyzqs7DSHnixA";
    }

}
