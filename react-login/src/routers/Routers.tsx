import React, { Suspense } from 'react';
import { Navigate, Route, Routes } from 'react-router';
import LoginComponent from '../components/security/LoginComponent';
import MainComponenet from '../components/MainComponent';
import OauthLoginComponent from '../components/oauth/OauthLoginComponent';
import LoginSucessComponent from '../components/security/LoginSuccessComponent';

const Routers = (props: any) => {
	return (
		<Routes>
			<Route path='/' element={<Navigate replace to='/main' {...props} />} />
			<Route path={'main'} element={<MainComponenet {...props} />} />
			<Route path={'login'} element={<LoginComponent {...props} />} />
			<Route path={'loginSuccess'} element={<LoginSucessComponent {...props} />} />
			<Route path={'oAuthLogin'} element={<OauthLoginComponent {...props} />} />
		</Routes>
	);
};
export default Routers;
