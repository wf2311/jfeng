package com.wf2311.jfeng.exception;

/**
 * 自定义异常
 * @author: wf2311
 */
public class CustomException extends RuntimeException {
    protected int code;

    public CustomException() {
    }

    /**
     * WfException
     *
     * @param code 错误代码
     */
    public CustomException(int code) {
        super("code=" + code);
        this.code = code;
    }

    /**
     * WfException
     *
     * @param message 错误消息
     */
    public CustomException(String message) {
        super(message);
    }

    /**
     * WfException
     *
     * @param cause 捕获的异常
     */
    public CustomException(Throwable cause) {
        super(cause);
    }

    /**
     * WfException
     *
     * @param message 错误消息
     * @param cause   捕获的异常
     */
    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * WfException
     *
     * @param code    错误代码
     * @param message 错误消息
     */
    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
