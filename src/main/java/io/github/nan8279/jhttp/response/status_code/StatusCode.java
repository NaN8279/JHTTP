package io.github.nan8279.jhttp.response.status_code;

/**
 * Response status codes.
 */
public enum StatusCode {
    /**
     * Default status code.
     */
    STATUS_200(200, "OK"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_300(300, "Multiple Choice"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_301(301, "Moved Permanently"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_302(302, "Found"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_303(303, "See Other"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_307(307, "Temporary Redirect"),
    /**
     * This status code should only be used with {@link io.github.nan8279.jhttp.response.special_responses.RedirectResponse}
     */
    STATUS_308(308, "Permanent Redirect"),
    STATUS_400(400, "Bad Request"),
    STATUS_401(401, "Unauthorized"),
    STATUS_403(403, "Forbidden"),
    STATUS_404(404, "Not Found"),
    STATUS_405(405, "Method Not Allowed"),
    STATUS_406(406, "Not Acceptable"),
    STATUS_407(407, "Proxy Authentication Required"),
    STATUS_408(408, "Request Timeout"),
    STATUS_409(409, "Conflict"),
    STATUS_410(410, "Gone"),
    STATUS_411(411, "Length Required"),
    STATUS_412(412, "Precondition Failed"),
    STATUS_413(413, "Payload Too Large"),
    STATUS_414(414, "URI Too Long"),
    STATUS_415(415, "Unsupported Media Type"),
    STATUS_416(416, "Range Not Satisfiable"),
    STATUS_417(417, "Expectation Failed"),
    STATUS_418(418, "I'm a teapot"),
    STATUS_421(421, "Misdirected Request"),
    STATUS_425(425, "Too Early"),
    STATUS_426(426, "Upgrade Required"),
    STATUS_428(428, "Precondition Required"),
    STATUS_429(429, "Too Many Requests"),
    STATUS_431(431, "Request Header Fields Too Large"),
    STATUS_451(451, "Unavailable For Legal Reasons"),
    STATUS_500(500, "Internal Server Error"),
    STATUS_501(501, "Not Implemented Error"),
    STATUS_502(502, "Bad Gateway"),
    STATUS_503(503, "Service Unavailable"),
    STATUS_504(504, "Gateway Timeout"),
    STATUS_505(505, "HTTP Version Not Supported");

    final private int statusCode;
    final private String statusMessage;

    /**
     * @param statusCode the status code.
     * @param statusMessage the status message.
     */
    StatusCode(int statusCode, String statusMessage) {
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /**
     * @return the status code.
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * @return the status message.
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @return true if the status code starts with a 5.
     */
    public boolean isServerError() {
        return String.valueOf(getStatusCode()).charAt(0) == '5';
    }

    /**
     * @return true if the status code starts with a 3.
     */
    public boolean isRedirectCode() {
        return String.valueOf(getStatusCode()).charAt(0) == '3';
    }
}
