/**
 * 카카오 로그인 관련 Typescript
 */
export declare module OAuth2KakaoLoginType {
	interface LoginResponseType {
		code: string; // 토큰 받기 요청에 필요한 인가 코드
		error: string; // 인증 실패 시 반환되는 에러 코드
		errorDescription: string; // 인증 실패 시 반환되는 에러 메시지
		state: string; // 요청 시 전달한 state 값과 동일한 값
	}
}
