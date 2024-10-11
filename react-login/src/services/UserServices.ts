import axios, { AxiosResponse } from "axios";
import { UserType } from "../types/UserTypes";
import { CommonType } from "../types/common/CommonType";

const SERVER_DEFAULT_API = "http://localhost:8080/";

class UserService {

    selectUserList = async (userInfo: UserType.UserInfoType, token: string): Promise<AxiosResponse<Boolean & CommonType.loginApiResponseType, any>> => {
        try {

            return axios({
                method: "post",
                url: `${SERVER_DEFAULT_API}api/v1/user/user`,
                data: userInfo,
                headers: {
                    "Accept": 'application/json',
                    'content-Type': 'application/json',
                    "Authorization": `BEARER ${token}`
                },
            });

        } catch (error) {
            throw new Error(`Error posting data:${error}`)
        }
    };

}
export default new UserService();