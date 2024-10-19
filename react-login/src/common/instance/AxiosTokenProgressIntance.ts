import axios from "axios";


// 인터셉터를 이용하지 않을 블랙 리스트를 문자열 배열로 구성합니다.
const BLACK_LIST_URL = [
	'/api/v1/user/login'
];


// 블랙리스트에 포함되지 않는 URL인지 확인하는 함수
const isNotBlackListed = (url?: string) => !BLACK_LIST_URL.some((blackUrl) => url?.includes(blackUrl));

const AxiosTokenProgressIntance = axios.create({
	baseURL: 'http://localhost:8080',
	timeout: 5000,
	headers: {
		'Content-Type': 'application/json',
		'Access-Control-Allow-Credentials': true,
	},
});

/**
 * 요청 인터셉터 추가
 * - 요청이 서버로 전송되기 전에 실행이 됩니다.
 */
AxiosTokenProgressIntance.interceptors.request.use(
	(config) => {
		const { url, headers } = config
		// 해당 과정에서 BLACK_LIST 내에 요청 URL이 포함되지 않은 경우만 수행이 됩니다.
		if (isNotBlackListed(url)) {
			console.log('[+] 로그인 수행 이후를 처리합니다. ', config);
			const accessToken = localStorage.getItem("accessToken");
			const refreshToken = localStorage.getItem("refreshToken");
			if (!accessToken) {
				alert("접근 권한이 없습니다.");
				window.location.href = '/login';				// 로그인 페이지로 이동합니다.
				return config;
			}
			headers["Authorization"] = accessToken;				// header 내에 accessToken를 세팅합니다.
			headers['x-refresh-token'] = refreshToken;	// header 내에 refreshToken 세팅합니다.

			console.log(`refreshToken  : ${refreshToken}`)

		}
		return config;
	},
	(error) => {
		console.log('error :: ', error.config);

		if (isNotBlackListed(error.config.url)) {
			console.log('[-] 요청 전달 실패한 경우 수행이 됩니다. ', error);
		}
		return Promise.reject(error);
	},
);

/**
 * 응답 인터셉터 추가
 * - 서버로부터 응답을 받은 후, 그 응답이 then/catch 핸들러로 전달되기 전에 실행됩니다.
 */
AxiosTokenProgressIntance.interceptors.response.use(
	(response) => {
		console.log('[+] 응답이 정상적으로 수행된 경우 수행이 됩니다. ', response);
		if (isNotBlackListed(response.config.url)) {
			if (response.status === 200 && response.data.status === 403) {
				console.log("[-] 권한 관련 에러가 발생한 경우 입니다.")
				alert(response.data.reason);
				// window.location.href = '/login';				// 로그인 페이지로 이동합니다.
			}
		}
		return response;
	},
	(error) => {

		console.log('error :: ', error)

		if (isNotBlackListed(error.config.url)) {
			console.log('[-] 응답이 실패한 경우 수행이 됩니다. ', error);
		}
		return Promise.reject(error);
	},
);

export default AxiosTokenProgressIntance;