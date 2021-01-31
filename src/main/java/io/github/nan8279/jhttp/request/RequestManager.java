package io.github.nan8279.jhttp.request;

import io.github.nan8279.jhttp.JHTTP;
import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.request.request_headers.Command;
import io.github.nan8279.jhttp.request.request_headers.Protocol;
import io.github.nan8279.jhttp.request.request_headers.RequestHeaders;
import io.github.nan8279.jhttp.request.request_types.GetRequest;
import io.github.nan8279.jhttp.request.request_types.PostRequest;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.status_code.StatusCode;

import java.util.HashMap;

/**
 * A request manager. Should only be used internally.
 *
 * Use {@link JHTTP#getManager()} to get the request manager of a HTTP server.
 */
public class RequestManager {
    final private HashMap<String, RequestHandler> handlers = new HashMap<>();

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
    public Response parseRequest(RequestHeaders request, Client client) {
        Response response;

        if (request.getProtocol() == Protocol.HTTP_1) {
            return new Response(StatusCode.STATUS_426);
        } else if (request.getProtocol() == Protocol.HTTP_2) {
            return new Response(StatusCode.STATUS_505);
        }

        if (request.getCommand() == Command.GET) {
            response = this.parseRequest(new GetRequest(request, client.getCookies()));
        } else if (request.getCommand() == Command.POST) {
            response = this.parseRequest(new PostRequest(request, client.getCookies()));
        } else {
            return new Response(StatusCode.STATUS_501);
        }
        if (response == null) {
            return new Response(StatusCode.STATUS_404);
        }

        return response;
    }
}
