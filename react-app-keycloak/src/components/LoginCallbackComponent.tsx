import { useEffect } from 'react';
import { useNavigate } from 'react-router';

const CallbackComponent = () => {
	const navigate = useNavigate();

	useEffect(() => {
		const handleCallback = () => {
			// URL 프래그먼트에서 토큰 정보 추출
			const fragment = window.location.hash.substring(1);

			console.log('fragment : ', fragment);
			const params = new URLSearchParams(fragment);

			const accessToken = params.get('access_token');
			const tokenType = params.get('token_type');
			const state = params.get('state');

			console.log(accessToken);
			console.log(tokenType);
			console.log(state);
			localStorage.setItem('accessToken', accessToken!);
		};

		handleCallback();
	}, [navigate]);

	return <div>Processing login...</div>;
};
export default CallbackComponent;
