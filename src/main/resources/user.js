Cookie = Java.type("io.github.nan8279.jhttp.cookies.Cookie");
RedirectResponse = Java.type("io.github.nan8279.jhttp.response.special_responses.RedirectResponse");
StatusCode = Java.type("io.github.nan8279.jhttp.response.status_code.StatusCode");

function User(request) {
    var cookies = request.getClient().getCookies(false);

    this.getCookie = function(name) {
        for (i = 0; i < cookies.size(); i++) {
            if (cookies.get(i).getName().equals(name)) {
                return cookies.get(i).getValue();
            }
        }
        return null;
    }

    this.setCookie = function(name, value) {
        request.getClient().setCookie(new Cookie(name, value));
    }

    this.getCookies = function() {
        return cookies.toArray();
    }

    this.redirect = function(url) {
        request.getClient().sendResponse(new RedirectResponse(url, StatusCode.STATUS_302));
    }
}