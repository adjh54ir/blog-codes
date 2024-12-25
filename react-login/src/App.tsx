import React, { useEffect, useRef } from 'react';
import { BrowserRouter } from 'react-router-dom';
import Layout from './layout/Layout';
import axios from 'axios';
const App = (props: any) => {
	const interceptorsRegistered = useRef(false);

	useEffect(() => {
		if (!interceptorsRegistered.current) {
			axiosInterceptorHandler.regist();
			interceptorsRegistered.current = true;
		}
	}, []);


	/** 
	 * Axios의 Interceptor를 구성합니다.
	*/
	const axiosInterceptorHandler = (() => {
		return {
			regist: () => {
				// /**
				//  * 2. 요청 이전 처리 방법 : 요청 인터셉터
				//  */
				// axios.interceptors.request.use(
				// 	// 요청이 전달되기 전에 작업 수행
				// 	(config) => {
				// 		console.log("[+] 요청 전달 이전에 수행이 됩니다. ", config)
				// 		return config;
				// 	},
				// 	// 요청 오류가 있는 작업 수행
				// 	(error) => {
				// 		console.log("[-] 요청 중 오류가 발생되었을때 수행이 됩니다. ", error)
				// 		return Promise.reject(error);
				// 	}
				// );
				// /**
				//  * 3. 응답 후 처리 방법 : 응답 인터셉터
				//  */
				// axios.interceptors.response.use(
				// 	(response) => {
				// 		console.log("[+] 응답이 정상적으로 수행된 경우 수행이 됩니다. ", response)
				// 		// 2xx 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
				// 		// 응답 데이터가 있는 작업 수행
				// 		return response;
				// 	},
				// 	(error) => {
				// 		console.log("[-] 응답이 실패한 경우 수행이 됩니다. ", error)
				// 		// 2xx 외의 범위에 있는 상태 코드는 이 함수를 트리거 합니다.
				// 		// 응답 오류가 있는 작업 수행
				// 		return Promise.reject(error);
				// 	}
				// );
			}
		}
	})();


	return (
		<BrowserRouter basename={props.basename}>
			<Layout {...props} />
		</BrowserRouter>
	);
};
export default App;
