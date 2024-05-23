package com.adjh.springbootcommon.commons.enums;

/**
 * Please explain the class!!
 *
 * @author : lee
 * @fileName : OperationEnum
 * @since : 24. 5. 22.
 */
public enum OperationEnum {

    PLUS {
        public int apply(int x, int y) {
            return x + y;
        }
    },
    MINUS {
        public int apply(int x, int y) {
            return x - y;
        }
    },
    TIMES {
        public int apply(int x, int y) {
            return x * y;
        }
    },
    DIVIDE {
        public int apply(int x, int y) {
            return x / y;
        }
    };

    public abstract int apply(int x, int y);
}
