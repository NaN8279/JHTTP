package io.github.nan8279.jhttp.request;

import io.github.nan8279.jhttp.client.Client;
import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.request.raw_request.RawRequest;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A HTTP request.
 */
public abstract class Request {
    final private RawRequest headers;
    final private Client client;
    final private ArrayList<Cookie> cookies = new ArrayList<>();

    /**
     * @param headers the request.
     * @param cookies the cookies the user has.
     * @param client the client that is requesting.
     */
    public Request(RawRequest headers, ArrayList<Cookie> cookies, Client client) {
        this.headers = headers;
        this.cookies.addAll(cookies);
        this.client = client;
    }

    /**
     * @return the request headers.
     */
    public RawRequest getHeaders() {
        return headers;
    }

    /**
     * @return the data given in the HTTP request.
     */
    public HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();

        if (getHeaders().getData() == null) {
            return data;
        }

        for (String var : getHeaders().getData().split("&")) {
            try {
                String name = var.split("=")[0];
                String value = var.split("=")[1];
                data.put(name.replace("+", " "), value.replace(
                        "+", " "));
            } catch (ArrayIndexOutOfBoundsException ignored) {}
        }

        return data;
    }

    /**
     * @return the client that is requesting.
     */
    public Client getClient() {
        return client;
    }
}
