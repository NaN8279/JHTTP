package io.github.nan8279.jhttp.response.special_responses;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.exceptions.InvalidStatusCodeException;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

/**
 * This is a special response. When send, it will redirect the user to the specified location.
 */
public class RedirectResponse extends Response {
    final private String location;

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

        this.location = location;
    }

    @Override
    protected String generateResponse() {
        StringBuilder response =
                new StringBuilder("HTTP/1.1" + " " + status.getStatusCode() + " " + status.getStatusMessage() + "\r\n" +
                        "Connection: Closed\r\n" +
                        "Server: JHTTP/1.0\r\n" +
                        "Content-Length: " + data.length() + "\r\n" +
                        "Content-Type: text/plain; charset=utf-8\r\n" +
                        "Location: " + location + "\r\n");

        for (Cookie cookie : getCookies()) {
            response.append(cookie.toString());
            response.append("\r\n");
        }

        response.append("\r\n");
        response.append(data);

        return response.toString();
    }
}
