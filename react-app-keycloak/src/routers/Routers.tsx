import React, { Suspense, lazy } from 'react';
import { Navigate, Route, Routes } from 'react-router';
import LoginComponent from '../components/LoginComponent.tsx';
import LoginCallbackComponent from '../components/LoginCallbackComponent.tsx';

const Routers = (props: any) => {
	return (
		<Suspense fallback={<div>Loading...</div>}>
			<Routes>
				<Route path='/' element={<Navigate replace to='/login' {...props} />} />
				<Route path='/login' element={<LoginComponent {...props} />} />
				<Route path='/login/callback' element={<LoginCallbackComponent {...props} />} />
			</Routes>
		</Suspense>
	);
};

export default Routers;
