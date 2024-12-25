import axios, { AxiosResponse } from 'axios';
import { UserType } from '../types/UserTypes';
import { CommonType } from '../types/common/CommonType';
import AxiosJwtInstance from '../common/instance/AxiosJwtInstance';

/**
 * 사용자 관리 서비스
 */
class OAuthLoginService {
	/**
	 * 사용자 리스트를 조회합니다.
	 * @param userInfo
	 * @returns
	 */
	selectUserList = async (
		userInfo: UserType.UserInfoType,
	): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType[], any>> => {
		return await AxiosJwtInstance.post(`/api/v1/user/user`, userInfo);
	};
}
export default new OAuthLoginService();
