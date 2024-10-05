import React, { useEffect } from 'react';
import { BrowserRouter } from 'react-router-dom';
import Layout from './layout/Layout';
const App = (props: any) => {
	useEffect(() => {}, []);

	return (
		<BrowserRouter basename={props.basename}>
			<Layout {...props} />
		</BrowserRouter>
	);
};
export default App;
