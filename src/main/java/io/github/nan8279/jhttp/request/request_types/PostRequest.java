package io.github.nan8279.jhttp.request.request_types;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.request.raw_request.RawRequest;
import io.github.nan8279.jhttp.request.Request;

import java.util.ArrayList;

/**
 * A HTTP POST request.
 */
public class PostRequest extends Request {

    /**
     * @param headers the request.
     * @param cookies the cookies the user has.
     * @param client the client that is requesting.
     */
    public PostRequest(RawRequest headers, ArrayList<Cookie> cookies, Client client) {
        super(headers, cookies, client);
    }
}
