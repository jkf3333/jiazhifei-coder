package com.jiazhifei.coder.core.exception;

/**
 * coder的异常
 *
 * @author jiakunfeng
 */
public class CoderException extends RuntimeException {
    public CoderException(String message, Exception e) {
        super(message, e);
    }

}
