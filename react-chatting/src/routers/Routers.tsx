import React, { Suspense } from 'react';
import { Navigate, Route, Routes } from 'react-router';
import MainComponenet from '../components/MainComponent';
import SocketIoComponent from '../components/socketio/SocketIoComponent';

const Routers = (props: any) => {
	return (
		<Suspense fallback={<></>}>
			<Routes>
				<Route path='/' element={<Navigate replace to='/main' {...props} />} />
				<Route path={'main'} element={<MainComponenet {...props} />} />
				<Route path={'socketIo'} element={<SocketIoComponent {...props} />} />
			</Routes>
		</Suspense>
	);
};
export default Routers;
