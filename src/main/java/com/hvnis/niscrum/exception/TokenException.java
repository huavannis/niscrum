package com.hvnis.niscrum.exception;

/**
 * @author hvnis
 */

public class TokenException extends RuntimeException {

    private static final long serialVersionUID = 5290638440107199234L;

    public TokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenException(String message) {
        super(message);
    }

    public TokenException(Throwable cause) {
        super(cause);
    }
}
