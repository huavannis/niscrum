package com.hvnis.niscrum.exception;

/**
 * @author hvnis
 */

public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 5290638440107199234L;

    public SystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemException(String message) {
        super(message);
    }

    public SystemException(Throwable cause) {
        super(cause);
    }
}
