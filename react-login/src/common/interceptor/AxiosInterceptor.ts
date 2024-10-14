import axios from "axios";

const AxiosInterceptor = axios.create({
    baseURL: 'http://localhost:8080',
    timeout: 5000,
    headers: {
        'Content-Type': 'application/json',
    }
});
// 요청 인터셉터 추가
AxiosInterceptor.interceptors.request.use(
    (config) => {
        console.log("[+] 요청 전달 이전에 수행이 됩니다. ", config)
        return config;
    },
    (error) => {
        console.log("[-] 요청 중 오류가 발생되었을때 수행이 됩니다. ", error)
        return Promise.reject(error);
    }
);

// 응답 인터셉터 추가
AxiosInterceptor.interceptors.response.use(
    (response) => {
        console.log("[+] 응답이 정상적으로 수행된 경우 수행이 됩니다. ", response)
        return response;
    },
    (error) => {
        console.log("[-] 응답이 실패한 경우 수행이 됩니다. ", error)
        return Promise.reject(error);
    }
);
export default AxiosInterceptor;
