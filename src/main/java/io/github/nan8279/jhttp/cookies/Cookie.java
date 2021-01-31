package io.github.nan8279.jhttp.cookies;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Contains info about a cookie.
 */
public class Cookie {
    final private String name;
    final private String value;
    private ZonedDateTime expireDate = null;

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
                    "; Expires=" + getExpireDate().format(DateTimeFormatter.RFC_1123_DATE_TIME);
        }
        return "Set-Cookie: " + getName() + "=" + getValue();
    }
}
