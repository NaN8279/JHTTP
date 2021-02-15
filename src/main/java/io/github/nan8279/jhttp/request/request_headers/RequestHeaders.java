package io.github.nan8279.jhttp.request.request_headers;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.cookies.Cookie;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Contains info about a HTTP request.
 */
public class RequestHeaders {
    final private Command command;
    final private Protocol protocol;
    final private String headers;
    final private ArrayList<Cookie> cookies = new ArrayList<>();
    final private String data;
    private String URI;

    /**
     * @param headers the headers given in the request.
     * @param payload the payload given in the request.
     */
    public RequestHeaders(String headers, String payload) {
        this.headers = headers;
        String fullCommand = headers.split("\n")[0];

        command = Command.fromString(fullCommand.split(" ")[0]);
        URI = URLDecoder.decode(fullCommand.split(" ")[1], StandardCharsets.UTF_8);

        if (getCommand() == Command.GET || getCommand() == Command.HEAD) {
            if (URI.split("\\?").length > 1) {
                data = URI.split("\\?")[1];
            } else {
                data = null;
            }
            URI = URI.split("\\?")[0];
        } else {
            data = URLDecoder.decode(payload, StandardCharsets.UTF_8);
        }

        protocol = Protocol.fromString(fullCommand.split(" ")[2].trim());

        for (String header : headers.split("\n")) {
            if (header.startsWith("Cookie: ")) {
                String cookieHeader = header.split(":")[1].trim();
                for (String cookie : cookieHeader.split(";")) {
                    String name = cookie.split("=")[0].trim();
                    String value = cookie.split("=")[1].trim();
                    cookies.add(new Cookie(name, value));
                }
            }
        }
    }

    /**
     * This should only be used internally.
     * Use {@link Client#getCookies()} instead.
     *
     * @return the cookies the client gave.
     */
    public ArrayList<Cookie> getCookies() {
        return cookies;
    }

    /**
     * @return the raw HTTP request headers.
     */
    public String getRawHeaders() {
        return headers;
    }

    /**
     * @return the command in the request.
     */
    public Command getCommand() {
        return command;
    }

    /**
     * @return the protocol in the request.
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * @return the URI in the request.
     */
    public String getURI() {
        return URI;
    }

    /**
     * This should only be used internally.
     * Use {@link io.github.nan8279.jhttp.request.Request#getData()} instead.
     *
     * @return the data given in the request. May be null.
     */
    public String getData() {
        return data;
    }
}
