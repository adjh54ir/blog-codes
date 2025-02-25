package com.adjh.springbootexternalnetwork.config.feign;

import lombok.extern.slf4j.Slf4j;

/**
 * OpenFeign Header 설정 환경 파일
 *
 * @author : jonghoon
 * @fileName : FeignClientHeaderConfig
 * @since : 25. 2. 13.
 */
@Slf4j
//@Configuration
public class FeignClientHeaderConfig {

//    /**
//     * 공통 FeignClient Header 정책 설정
//     *
//     * @return
//     */
//    @Bean
//    public RequestInterceptor requestHeaderInterceptor() {
//
//        log.warn("요청 인터셉터 수행 시점 >>>>>>>>>>>>>>>>>>");
//        return requestTemplate -> {
//            // Bearer 토큰 추가
////            requestTemplate.header("Authorization", "Bearer " + getTempAccessToken());
////            // 공통 헤더 추가
////            requestTemplate.header("x-api-key", "your-api-key");
////            requestTemplate.header("Custom-Header", "common-value");
//        };
//    }
//
//    /**
//     * TODO: 삭제 예정
//     *
//     * @return
//     */
//    private String getTempAccessToken() {
//        return "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJLbHVfNEVEamQzQnRseUdpaUtabTNFUTF0cXZReDNGOUN0WGxEUXJ0QUpBIn0.eyJleHAiOjE3Mzk1MDI4NjUsImlhdCI6MTczOTQ5OTI2NSwianRpIjoiMTU1MTIyMGMtN2IzMC00MDA4LThlMGUtYWU0ZWZkZjY2OTRlIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo5MDAxL3JlYWxtcy9kZXYtcmVhbG0iLCJhdWQiOlsicmVhbG0tbWFuYWdlbWVudCIsImJyb2tlciIsImFjY291bnQiXSwic3ViIjoiOTcyMDRhYTItMTE2MC00MjQzLTkwM2ItODFiYTJiZTE0ZGUwIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoic3ByaW5nLWJvb3QtYXBwIiwic2lkIjoiMDM1ZDM3YTktYzEyMC00YjA3LWI2ZWItMDVhYjkzYjFlNDhhIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyJodHRwOi8vbG9jYWxob3N0OjgwODAiXSwicmVhbG1fYWNjZXNzIjp7InJvbGVzIjpbIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iLCJkZWZhdWx0LXJvbGVzLWRldi1yZWFsbSJdfSwicmVzb3VyY2VfYWNjZXNzIjp7InJlYWxtLW1hbmFnZW1lbnQiOnsicm9sZXMiOlsidmlldy1pZGVudGl0eS1wcm92aWRlcnMiLCJ2aWV3LXJlYWxtIiwibWFuYWdlLWlkZW50aXR5LXByb3ZpZGVycyIsImltcGVyc29uYXRpb24iLCJyZWFsbS1hZG1pbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIiwibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwic3ByaW5nLWJvb3QtYXBwIjp7InJvbGVzIjpbInNwcmluZy1ib290LWFwcC1zdWItcm9sZSJdfSwiYnJva2VyIjp7InJvbGVzIjpbInJlYWQtdG9rZW4iXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJ2aWV3LWFwcGxpY2F0aW9ucyIsInZpZXctY29uc2VudCIsInZpZXctZ3JvdXBzIiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJkZWxldGUtYWNjb3VudCIsIm1hbmFnZS1jb25zZW50Iiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSBlbWFpbCIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYW1lIjoiVXBkYXRlZCBVc2VyIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic3ViYWRtaW4iLCJnaXZlbl9uYW1lIjoiVXBkYXRlZCIsImZhbWlseV9uYW1lIjoiVXNlciIsImVtYWlsIjoidXBkYXRlZEBleGFtcGxlLmNvbSJ9.IqfTKGmHqoa6j063e7RwmOmQbU21_joYwB9TIUH8vWaGHBL19IUCLYW25pRYa5OwJbH_ryuZZ08Phb7dTHEA4cvxcSzDxRnxyoAKQovNpEU7oi5CbeBUnNjXxS4exXK104WNIwX8UcpOaPLZJbkS6VS40Xlwpqy0grMtaP-7tPXOOnBVEGHnteWSAP4SEFPMoSWKf0iEWMCs4fqkasnoaTXQ6nrbD-dNhyhcqGN0-dI2eauCCQendj6AWTCQlrcPksAntT2yQ5n81DHmNyGnQH7FhzJlkSnB2VB2cZCN91kq9HhEWDBXZlF6aPBLZBMflf9-M9kHHtXkie0k8ZO_fw";
//    }

}
