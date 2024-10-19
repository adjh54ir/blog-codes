import axios from 'axios';

// 인터셉터를 이용하지 않을 블랙 리스트를 문자열 배열로 구성합니다.
const BLACK_LIST_URL = [
	'/api/v1/user/login'
];

// 블랙리스트에 포함되지 않는 URL인지 확인하는 함수
const isNotBlackListed = (url?: string) => !BLACK_LIST_URL.some((blackUrl) => url?.includes(blackUrl));

/**
 * Axios 인스턴스를 생성합니다.
 * - 이 인스턴스는 기본 URL, 타임아웃, 헤더 등의 설정을 포함합니다.
 */
const AxiosBlackListIntance = axios.create({
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
AxiosBlackListIntance.interceptors.request.use(
	(config) => {
		// 해당 과정에서 BLACK_LIST 내에 요청 URL이 포함되지 않은 경우만 수행이 됩니다.
		if (isNotBlackListed(config.url)) {
			console.log('[+] 요청 전달 이전에 수행이 됩니다. ', config);
		}


		return config;
	},
	(error) => {

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
AxiosBlackListIntance.interceptors.response.use(
	(response) => {
		if (isNotBlackListed(response.config.url)) {
			console.log('[+] 응답이 정상적으로 수행된 경우 수행이 됩니다. ', response);
		}
		return response;
	},
	(error) => {
		if (isNotBlackListed(error.config.url)) {
			console.log('[-] 응답이 실패한 경우 수행이 됩니다. ', error);
		}
		return Promise.reject(error);
	},
);
export default AxiosBlackListIntance;
