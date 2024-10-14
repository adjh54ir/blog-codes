import axios from 'axios';

const BLACK_LIST = ['/api/v1/user/login'];

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
		// BLACK_LIST 내에 요청 URL이 포함되는지 확인합니다.
		if (!BLACK_LIST.some((url) => config.url?.includes(url))) {
			console.log('[+] 요청 전달 이전에 수행이 됩니다. ', config);
		}

		return config;
	},
	(error) => {
		console.log('[-] 요청 중 오류가 발생되었을때 수행이 됩니다. ', error);
		return Promise.reject(error);
	},
);

/**
 * 응답 인터셉터 추가
 * - 서버로부터 응답을 받은 후, 그 응답이 then/catch 핸들러로 전달되기 전에 실행됩니다.
 */
AxiosBlackListIntance.interceptors.response.use(
	(response) => {
		console.log('[+] 응답이 정상적으로 수행된 경우 수행이 됩니다. ', response);
		return response;
	},
	(error) => {
		console.log('[-] 응답이 실패한 경우 수행이 됩니다. ', error);
		return Promise.reject(error);
	},
);
export default AxiosBlackListIntance;
