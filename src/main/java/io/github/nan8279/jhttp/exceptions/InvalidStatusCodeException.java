package io.github.nan8279.jhttp.exceptions;

import io.github.nan8279.jhttp.response.status_code.StatusCode;

/**
 * Will be thrown when an invalid status code has been given.
 */
public class InvalidStatusCodeException extends Exception {

    /**
     * @param code the status code.
     */
    public InvalidStatusCodeException(StatusCode code) {
        super("Invalid status code: " + code.getStatusCode());
    }
}
