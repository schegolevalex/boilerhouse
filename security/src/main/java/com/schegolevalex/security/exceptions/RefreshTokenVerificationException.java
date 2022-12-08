package com.schegolevalex.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class RefreshTokenVerificationException extends AuthenticationException {

    public RefreshTokenVerificationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public RefreshTokenVerificationException(String msg) {
        super(msg);
    }
}
