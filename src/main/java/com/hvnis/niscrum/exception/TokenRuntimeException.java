package com.hvnis.niscrum.exception;

/**
 * @author hvnis
 */
public class TokenRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 5290638440107199234L;

    public TokenRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenRuntimeException(String message) {
        super(message);
    }

    public TokenRuntimeException(Throwable cause) {
        super(cause);
    }
}
