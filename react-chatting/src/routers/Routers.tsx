import React, { Suspense, lazy } from 'react';
import { Navigate, Route, Routes } from 'react-router';

const MainComponent = lazy(() => import('../components/MainComponent'));
const SocketIoRedisComponent = lazy(() => import('../components/socketio/redis/SocketIoRedisComponent'));
const SocketIoComponent = lazy(() => import('../components/socketio/SocketIoComponent'));
const SocketIoNameSpaceComponent = lazy(() => import('../components/socketio/SocketIoNamespaceComponent'));
const SocketIoRoomComponent = lazy(() => import('../components/socketio/SocketIoRoomComponet'));
const StompComponent = lazy(() => import('../components/stomp/StompComponent'));

const Routers = (props: any) => {
	return (
		<Suspense fallback={<div>Loading...</div>}>
			<Routes>
				<Route path='/' element={<Navigate replace to='/main' {...props} />} />
				<Route path='/main' element={<MainComponent {...props} />} />
				<Route path='/socketIo' element={<SocketIoComponent {...props} />} />
				<Route path='/socketIo/room' element={<SocketIoRoomComponent {...props} />} />
				<Route path='/socketIo/namespace' element={<SocketIoNameSpaceComponent {...props} />} />
				<Route path='/socketIo/redis' element={<SocketIoRedisComponent {...props} />} />
				<Route path='/stomp' element={<StompComponent {...props} />} />
			</Routes>
		</Suspense>
	);
};

export default Routers;
