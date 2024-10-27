import { useNavigate } from 'react-router';

const MainComponenet = () => {
	const navigation = useNavigate();

	const moveToHandler = (() => {
		return {
			login: () => navigation('/login'),
			oAuthLogin: () => navigation('/oAuthLogin'),
		};
	})();

	return (
		<div style={{ width: '100%', textAlign: 'center' }}>
			<div style={{ marginBottom: 100 }}>
				<h1>로그인을 선택하세요.</h1>
			</div>
			<div style={{ flexDirection: 'row' }}>
				<button type='button' style={{ width: 200, height: 100 }} onClick={moveToHandler.login}>
					JWT 기반 일반 로그인
				</button>
				<button type='button' style={{ width: 200, height: 100, marginLeft: 100 }} onClick={moveToHandler.oAuthLogin}>
					OAuth 로그인
				</button>
			</div>
		</div>
	);
};
export default MainComponenet;
