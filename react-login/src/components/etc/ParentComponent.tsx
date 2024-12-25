import { useRef } from 'react';
import { ChildComponent } from './ChildComponent';

/**
 * 자식 컴포넌트에서 전달해오는 useImperativeHandle 값
 */
type ChildFuncType = {
	start: () => void;
	stop: () => void;
	pause: () => void;
};

const ParentComponent = () => {
	const componentRef = useRef<ChildFuncType>(null);

	/**
	 * 자식 컴포넌트의 함수를 호출
	 */
	const childCallHandler = (() => {
		return {
			start: () => {
				if (componentRef.current) {
					componentRef.current.start();
				}
			},
			stop: () => {
				if (componentRef.current) {
					componentRef.current.stop();
				}
			},
			pause: () => {
				if (componentRef.current) {
					componentRef.current.pause();
				}
			},
		};
	})();

	return (
		<div>
			<div style={{ marginBottom: 10 }}> 부모 컴포넌트 입니다.</div>
			<div style={{ marginBottom: 10 }}>
				<button onClick={childCallHandler.start}>시작</button>
				<button onClick={childCallHandler.pause}>중지</button>
				<button onClick={childCallHandler.stop}>종료</button>
			</div>
			<ChildComponent ref={componentRef} parentProps1='props' parentProps2='props2' />
		</div>
	);
};
export default ParentComponent;
