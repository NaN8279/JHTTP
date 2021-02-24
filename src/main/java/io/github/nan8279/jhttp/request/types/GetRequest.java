package io.github.nan8279.jhttp.request.types;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.cookie.Cookie;
import io.github.nan8279.jhttp.request.raw.RawRequest;
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
    public GetRequest(RawRequest headers, ArrayList<Cookie> cookies, Client client) {
        super(headers, cookies, client);
    }
}
