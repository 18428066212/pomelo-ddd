package com.pomelo.ddd.core.exception;

/**
 * 异常
 * 2020/8/8 11:35
 *
 * @author 何刚
 */
public class PomeloException extends RuntimeException {


    public PomeloException(String message) {
        super(message);
    }

    public PomeloException(String message, Throwable cause) {
        super(message, cause);
    }

    public PomeloException(Throwable cause) {
        super(cause);
    }
}
