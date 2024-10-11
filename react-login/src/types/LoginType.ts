export declare module LoginType {
	interface LoginType {
		userId: string;
		userPw: string;
	}
	interface loginApiResponseType {
		userInfo: any;
		token?: string;
		resultCode: string | number;
		failMsg: string;
	}
}
