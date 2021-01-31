package io.github.nan8279.jhttp.request.request_types;

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
     *
     * @param headers the request.
     * @param cookies the cookies the user has.
     */
    public GetRequest(RequestHeaders headers, ArrayList<Cookie> cookies) {
        super(headers, cookies);
    }
}
