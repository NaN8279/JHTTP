package io.github.nan8279.jhttp.cookie;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains info about a cookie.
 */
public class Cookie {
    final private String name;
    final private String value;
    private ZonedDateTime expireDate = null;
    private boolean serverMade = true;

    /**
     * Creates a new cookie with expire date.
     *
     * @param name the name of the cookie.
     * @param value the value of the cookie.
     * @param expireDate the expire date of the cookie.
     */
    public Cookie(String name, String value, ZonedDateTime expireDate) {
        this.name = name;
        this.value = value;
        this.expireDate = expireDate;
    }

    /**
     * Creates a new cookie.
     *
     * @param name the name of the cookie.
     * @param value the value of the cookie.
     */
    public Cookie(String name, String value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Creates cookies based off a request header.
     * Should only be used internally.
     *
     * @param requestHeader the request header.
     * @return the cookie.
     */
    public static ArrayList<Cookie> fromRequestHeader(String requestHeader) {
        String cookieHeader = requestHeader.split(":")[1].trim();
        ArrayList<Cookie> cookies = new ArrayList<>();

        for (String cookieString : cookieHeader.split(";")) {
            String name = cookieString.split("=")[0].trim();
            String value = cookieString.split("=")[1].trim();

            Cookie cookie = new Cookie(name, value);
            cookie.serverMade = false;
            cookies.add(cookie);
        }

        return cookies;
    }

    /**
     * @return the name of the cookie.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the cookie.
     */
    public String getValue() {
        return value;
    }

    /**
     * @return the expire date of the cookie. Returns null if no expire date.
     */
    public ZonedDateTime getExpireDate() {
        return expireDate;
    }

    /**
     * @return the header that will be added in the request.
     */
    @Override
    public String toString() {
        if (getExpireDate() != null) {
            return "Set-Cookie: " + getName() + "=" + getValue() +
                    "; Expires=" + getExpireDate().format(DateTimeFormatter.RFC_1123_DATE_TIME) +
                    "; SameSite=Lax";
        }
        return "Set-Cookie: " + getName() + "=" + getValue() + "; SameSite=Lax";
    }

    /**
     * @return true when the cookie is made by the server / program.
     */
    public boolean isServerMade() {
        return serverMade;
    }
}
