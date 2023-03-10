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

    public CoderException(String message) {
        super(message);
    }

    public static CoderException error(String message) {
        return new CoderException(message);
    }

    public static CoderException error(String message, Exception e) {
        return new CoderException(message, e);
    }
}
