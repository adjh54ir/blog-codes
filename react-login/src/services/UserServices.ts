import axios, { AxiosResponse } from 'axios';
import { UserType } from '../types/UserTypes';
import { CommonType } from '../types/common/CommonType';
import AxiosBlackListIntance from '../common/instance/AxiosBlackListIntance';
import AxiosTokenProgressIntance from '../common/instance/AxiosTokenProgressIntance';

class UserService {

	/**
	 * 사용자 리스트를 조회합니다.
	 * @param userInfo 
	 * @param accessToken 
	 * @param refreshToken 
	 * @returns 
	 */
	selectUserList = async (
		userInfo: UserType.UserInfoType,
	): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await AxiosTokenProgressIntance.post(`/api/v1/user/user`, userInfo,);
	};
}
export default new UserService();
