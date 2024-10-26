import axios, { AxiosResponse } from 'axios';
import { UserType } from '../types/UserTypes';
import { CommonType } from '../types/common/CommonType';
import AxiosJwtInstance from '../common/instance/AxiosJwtInstance';

/**
 * 사용자 관리 서비스
 */
class CorsTestService {
	/**
	 * 사용자 리스트를 조회합니다.
	 * @param userInfo
	 * @returns
	 */
	login = async (): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await axios.post(`http://localhost:8080/api/v1/user/login`, {});
	};

	/**
	 * 사용자 리스트를 조회합니다.
	 * @param userInfo
	 * @returns
	 */
	selectUserList = async (): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await axios.post(`http://localhost:8080/api/v1/user/user`, {});
	};
}
export default new CorsTestService();
