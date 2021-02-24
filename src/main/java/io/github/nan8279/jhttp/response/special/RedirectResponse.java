package io.github.nan8279.jhttp.response.special;

import io.github.nan8279.jhttp.exception.InvalidStatusCodeException;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.code.StatusCode;

/**
 * This is a special response. When send, it will redirect the user to the specified location.
 */
public class RedirectResponse extends Response {
    /**
     * @param location the location to redirect the user too.
     * @param status the status code to be sent with the response.
     * @throws InvalidStatusCodeException when a status has been given that is not a redirect status.
     * (does not start with a 3)
     */
    public RedirectResponse(String location, StatusCode status) throws InvalidStatusCodeException {
        super(status);

        if (!status.isRedirectCode()) {
            throw new InvalidStatusCodeException(status);
        }

        responseHeader.addCustomHeader("Location", location);
    }
}
