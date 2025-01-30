import React from 'react';
import { BrowserRouter } from 'react-router';
import Layout from './layout/Layout.tsx';

const App = (props: any) => {
	return (
		<BrowserRouter basename={props.basename}>
			<Layout {...props} />
		</BrowserRouter>
	);
};

export default App;
