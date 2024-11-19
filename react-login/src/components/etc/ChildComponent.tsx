import { forwardRef, useImperativeHandle } from 'react';

export const ChildComponent = forwardRef(
	({ parentProps1, parentProps2 }: { parentProps1: string; parentProps2: string }, ref) => {
		const stopwatchHandler = (() => {
			return {
				start: () => console.log('[+] 스탑워치를 시작합니다.'),
				stop: () => console.log('[+] 스탑워치를 종료합니다.'),
				pause: () => console.log('[+] 스탑워치를 일시정지합니다.'),
			};
		})();
		/**
		 * 부모 컴포넌트로 전달할 값
		 */
		useImperativeHandle(ref, () => ({
			start: stopwatchHandler.start, // 스탑워치를 시작합니다.
			pause: stopwatchHandler.pause, // 스탑워치를 멈춥니다.
			stop: stopwatchHandler.stop, // 스탑워치를 종료합니다.
		}));

		return <div>자식 컴포넌트를 출력합니다.</div>;
	},
);
