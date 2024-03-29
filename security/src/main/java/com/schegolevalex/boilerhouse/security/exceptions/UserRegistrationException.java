package com.schegolevalex.boilerhouse.security.exceptions;

import org.springframework.security.core.AuthenticationException;

public class UserRegistrationException extends AuthenticationException {

    public UserRegistrationException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public UserRegistrationException(String msg) {
        super(msg);
    }
}
