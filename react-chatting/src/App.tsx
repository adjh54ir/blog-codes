import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import Layout from './components/Layout';
const App = (props: any) => {
	useEffect(() => {
		console.log('[+] App ...');
	}, []);

	return (
		<BrowserRouter basename={props.basename}>
			<Layout {...props} />
		</BrowserRouter>
	);
};
export default App;
