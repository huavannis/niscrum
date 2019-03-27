package com.hvnis.niscrum.exception;

/**
 * @author hvnis
 */
public class SystemRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -4952426061114378110L;

    public SystemRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public SystemRuntimeException(String message) {
        super(message);
    }

    public SystemRuntimeException(Throwable cause) {
        super(cause);
    }
}
