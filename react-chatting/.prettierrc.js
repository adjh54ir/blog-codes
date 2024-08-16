/**
 * @name: .pretteierrc.js
 * @description: Prettier는 코드를 읽어들여서 사용자 옵션에 따라 코드를 다시 포맷팅하는 "코드 포맷터" 입니다.
 * @version: 2.0
 */
module.exports = {
	/**
	 * @template: printWidth: <int>
	 * @description: 코드 한줄의 개수
	 * 추천) 가독성을 위해 80자 이상을 사용하지 않는 것이 좋습니다.
	 * 추천) 코드 스타일 가이드에서 최대 줄 길이 규칙은 종종 100 또는 120으로 설정됩니다.
	 */
	printWidth: 80,

	/**
	 * @template: tabWidth: <int>
	 * @description: 들여쓰기 너비 수(탭을 사용할 경우 몇칸을 띄워줄지)
	 */
	tabWidth: 1,

	/**
	 * @template: useTabs: <bool>
	 * @description: 탭 사용 여부 (미사용 시 스페이스바로 간격조정을 해야함.)
	 */
	useTabs: true,

	/**
	 * @template: semi: <bool>
	 * @description: 명령문의 끝에 세미콜론(;)을 인쇄합니다.
	 * true: (;)를 추가함
	 * false: (;)를 지움
	 */
	semi: true,

	/**
	 * @template: singleQuote: <bool>
	 * @description: 큰따옴표("") 대신 작은따옴표('')를 사용여부
	 * true: 홀따옴표로 사용
	 * false: 큰따옴표로 사용
	 */
	singleQuote: true,

	/**
	 * @template: jsxSingleQuote: <bool>
	 * @description: JSX내에서 큰따옴표("") 대신 작은따옴표('')를 사용여부
	 * true: 홀따옴표로 사용
	 * false: 큰따옴표로 사용
	 */
	jsxSingleQuote: true,

	/**
	 * @template: trailingComma: "<es5|none|all>"
	 * @description: 객체나 배열을 작성하여 데이터를 넣을때, 마지막에 후행쉼표를 넣을지 여부
	 * es5: 후행쉼표 제외
	 * none: 후행쉼표 없음
	 * all: 후행쉼표 포함
	 */
	trailingComma: 'all',

	/**
	 * @template: jsxBracketSameLine: <bool>
	 * @description: ">" 다음 줄에 혼자 있는 대신 여러 줄 JSX 요소를 마지막 줄 끝에 넣습니다
	 * true: 줄넘김하지 않음
	 * false: 줄넘김을 수행
	 */
	jsxBracketSameLine: true,

	/**
	 * @template: bracketSpacing: <bool>
	 * @description: 개체 리터럴에서 대괄호 사이의 공백을 넣을지 여부
	 * true: 공백을 넣음 { foo: bar }
	 * false: 공백을 제외 {foo: bar}
	 */
	bracketSpacing: true,

	/**
	 * @template: bracketSpacing: <'always'| 'avoid'>
	 * 화살표 함수가 하나의 매개변수를 받을 때 괄호 사용여부
	 * always : 괄호 사용
	 * avoid : 괄호 제거
	 */
	// arrowParens: 'avoid',

	// endOfLine: 'auto', // EoF 방식, OS별로 처리 방식이 다름
	// htmlWhitespaceSensitivity: 'css', // HTML 공백 감도 설정
};
