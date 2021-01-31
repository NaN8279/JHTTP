package io.github.nan8279.jhttp.request;

import io.github.nan8279.jhttp.response.Response;

/**
 * A request handler.
 */
public interface RequestHandler {
    /**
     * Handles a HTTP request.
     *
     * @param request the HTTP request.
     * @return the response to be send to the user.
     */
    Response parseRequest(Request request);
}
