import React, { Suspense } from 'react';
import { Navigate, Route, Routes } from 'react-router';
import MainComponenet from '../components/MainComponent';

const Routers = (props: any) => {
	return (
		<Suspense fallback={<></>}>
			<Routes>
				<Route path='/' element={<Navigate replace to='/main' {...props} />} />
				<Route path={'main'} element={<MainComponenet {...props} />} />
			</Routes>
		</Suspense>
	);
};
export default Routers;
