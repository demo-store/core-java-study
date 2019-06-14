package com.ltpc.study.core.thread.ch8.ch2;

/**
 * Created with IntelliJ IDEA.
 *
 * @author liutong
 * @date 2019-06-14 15:49
 * Description:
 **/
public class RunnableDenyException extends RuntimeException {
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public RunnableDenyException(String message) {
        super(message);
    }
}
