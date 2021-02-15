package io.github.nan8279.jhttp_script.script;

import io.github.nan8279.jhttp.cookies.Cookie;

import java.util.ArrayList;

public class User {
    final private ArrayList<Cookie> cookies = new ArrayList<>();

    public User(ArrayList<Cookie> cookies) {
        this.cookies.addAll(cookies);
    }

    public String getCookie(String name) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                return cookie.getValue();
            }
        }
        return null;
    }

    public void setCookie(String name, String value) {
        cookies.add(new Cookie(name, value));
    }

    protected ArrayList<Cookie> getCookies() {
        return cookies;
    }
}
