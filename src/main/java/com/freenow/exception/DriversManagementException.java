package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.freenow.util.AppConstants.CONSTRAINTS_ARE_VIOLATED_MESSAGE;
import static com.freenow.util.AppConstants.ENTITY_NOT_FOUND_MESSAGE;

public class DriversManagementException {


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = CONSTRAINTS_ARE_VIOLATED_MESSAGE)
    public static class ConstraintsViolationException extends Exception {
        static final long serialVersionUID = -3387516993224229948L;

        public ConstraintsViolationException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ENTITY_NOT_FOUND_MESSAGE)
    public static class EntityNotFoundException extends Exception {
        static final long serialVersionUID = -3387516993334229948L;

        public EntityNotFoundException(String message) {
            super(message);
        }

    }
}
