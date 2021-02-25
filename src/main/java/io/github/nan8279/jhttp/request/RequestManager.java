package io.github.nan8279.jhttp.request;

import io.github.nan8279.jhttp.JHTTP;
import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.request.raw.Command;
import io.github.nan8279.jhttp.request.raw.Protocol;
import io.github.nan8279.jhttp.request.raw.RawRequest;
import io.github.nan8279.jhttp.request.types.GetRequest;
import io.github.nan8279.jhttp.request.types.PostRequest;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.code.StatusCode;

import java.util.HashMap;

/**
 * A request manager. Should only be constructed internally.
 *
 * Use {@link JHTTP#getManager()} to get the request manager of a HTTP server.
 */
public class RequestManager {
    final private HashMap<String, RequestHandler> handlers = new HashMap<>();
    final private HashMap<StatusCode, Response> errorPages = new HashMap<>();

    /**
     * Adds a request handler to the server.
     *
     * @param handler the request handler.
     * @param URI the URI to handle on.
     */
    public void addRequestHandler(RequestHandler handler, String URI) {
        handlers.put(URI, handler);
    }

    /**
     * Parses a HTTP request.
     *
     * @param request the request.
     * @return the HTTP response.
     */
    private Response parseRequest(Request request) {
        for (String URI : handlers.keySet()) {
            if (URI.equals(request.getHeaders().getURI())) {
                return handlers.get(URI).parseRequest(request);
            }
        }
        return null;
    }

    /**
     * Parses a HTTP request. Should only be used internally.
     *
     * @param request the request.
     * @param client the client.
     * @return the HTTP response.
     */
    public Response parseRequest(RawRequest request, Client client) {
        Response response;

        if (request.getProtocol() == Protocol.HTTP_1) {
            return handleError(StatusCode.STATUS_426);
        } else if (request.getProtocol() == Protocol.HTTP_2) {
            return handleError(StatusCode.STATUS_505);
        }

        if (request.getCommand() == Command.GET || request.getCommand() == Command.HEAD) {
            response = this.parseRequest(new GetRequest(request, client.getCookies(false), client));
        } else if (request.getCommand() == Command.POST) {
            response = this.parseRequest(new PostRequest(request, client.getCookies(false), client));
        } else {
            return handleError(StatusCode.STATUS_501);
        }

        if (response == null) {
            return handleError(StatusCode.STATUS_404);
        } else if (request.getCommand() == Command.HEAD) {
            response.setData("");
            return response;
        }

        return response;
    }

    /**
     * This replaces the error page for the given status code.
     *
     * @param statusCode the status code to replace the error page for.
     * @param response the response to send to the user when the status code occurs.
     */
    public void setErrorPage(StatusCode statusCode, Response response) {
        if (errorPages.containsKey(statusCode)) {
            errorPages.replace(statusCode, response);
        } else {
            errorPages.put(statusCode, response);
        }
    }

    /**
     * Returns the response that will be given to the user when the given error code occurs.
     * Should only be used internally.
     *
     * @param errorCode the error code.
     * @return the response that will be send to the user.
     */
    public Response handleError(StatusCode errorCode) {
        for (StatusCode statusCode : errorPages.keySet()) {
            if (statusCode == errorCode) {
                return errorPages.get(statusCode);
            }
        }
        return new Response(errorCode);
    }
}
