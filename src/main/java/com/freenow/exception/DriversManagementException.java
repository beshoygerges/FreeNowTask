package com.freenow.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.freenow.util.AppConstants.*;


public interface DriversManagementException {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = CONSTRAINTS_ARE_VIOLATED_MESSAGE)
    class ConstraintsViolationException extends Exception {
        static final long serialVersionUID = -3387516993224229948L;

        public ConstraintsViolationException(String message) {
            super(message);
        }
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = ENTITY_NOT_FOUND_MESSAGE)
    class EntityNotFoundException extends Exception {
        static final long serialVersionUID = -3387516993334229948L;

        public EntityNotFoundException(Long id) {
            super(ENTITY_NOT_FOUND_MESSAGE + "  " + id);
        }

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = CAR_IN_USE_MESSAGE)
    class CarAlreadyInUseException extends Exception {
        static final long serialVersionUID = -3387516993334229940L;

        public CarAlreadyInUseException() {
            super(CAR_IN_USE_MESSAGE);
        }

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = ILLEGAL_CAR_ACCESS_MESSAGE)
    class IllegalCarAccessException extends Exception {
        static final long serialVersionUID = -3387516993334229940L;

        public IllegalCarAccessException() {
            super(ILLEGAL_CAR_ACCESS_MESSAGE);
        }

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = CAR_NOT_ACQUIRED_MESSAGE)
    class CarNotAcquiredException extends Exception {
        static final long serialVersionUID = -3387516993334229940L;

        public CarNotAcquiredException() {
            super(CAR_NOT_ACQUIRED_MESSAGE);
        }

    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = CAR_ACQUIRER_LIMITATION_MESSAGE)
    class CarAcquirerLimitationException extends Exception {
        static final long serialVersionUID = -3387516993334229940L;

        public CarAcquirerLimitationException() {
            super(CAR_ACQUIRER_LIMITATION_MESSAGE);
        }

    }
}
