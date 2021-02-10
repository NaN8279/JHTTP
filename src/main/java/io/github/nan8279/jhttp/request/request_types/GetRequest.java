package io.github.nan8279.jhttp.request.request_types;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.request.request_headers.RequestHeaders;
import io.github.nan8279.jhttp.request.Request;

import java.util.ArrayList;

/**
 * A HTTP GET request.
 *
 */
public class GetRequest extends Request {
    /**
     * @param headers the request.
     * @param cookies the cookies the user has.
     * @param client the client that is requesting.
     */
    public GetRequest(RequestHeaders headers, ArrayList<Cookie> cookies, Client client) {
        super(headers, cookies, client);
    }
}
