export declare module LoginType {
	interface LoginType {
		userId: string;
		userPw: string;
	}
	interface loginApiResponseType {
		userInfo: any;
		accessToken?: string;
		refreshToken?: string;
		resultCode: string | number;
		failMsg: string;
	}
}
