package io.github.nan8279.jhttp_script.script;

import io.github.nan8279.jhttp.cookies.Cookie;

import java.util.ArrayList;

/**
 * Used to manage cookies of a user. May be used for other things in the future.
 */
public class User {
    final private ArrayList<Cookie> cookies = new ArrayList<>();

    public User(ArrayList<Cookie> cookies) {
        this.cookies.addAll(cookies);
    }

    /**
     * Looks up a cookie.
     *
     * @param name the name of the cookie.
     * @return the value of the cookie. null if the cookie does not exist.
     */
    public String getCookie(String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    /**
     * Sets a cookie for the user.
     *
     * @param name the name of the cookie to set.
     * @param value the value of the cookie to set.
     */
    public void setCookie(String name, String value) {
        cookies.add(new Cookie(name, value));
    }

    /**
     * @return a list of cookies the should have.
     */
    protected ArrayList<Cookie> getCookies() {
        return cookies;
    }
}
