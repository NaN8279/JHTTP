package io.github.nan8279.jhttp.request.request_types;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.request.request_headers.RequestHeaders;
import io.github.nan8279.jhttp.request.Request;

import java.util.ArrayList;

/**
 * A HTTP POST request.
 */
public class PostRequest extends Request {

    /**
     *
     * @param headers the request.
     * @param cookies the cookies the user has.
     */
    public PostRequest(RequestHeaders headers, ArrayList<Cookie> cookies) {
        super(headers, cookies);
    }
}
