package io.github.nan8279.jhttp_script.script;

import io.github.nan8279.jhttp.cookies.Cookie;
import io.github.nan8279.jhttp.request.RequestHandler;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.response_types.ResponseType;
import io.github.nan8279.jhttp.response.status_code.StatusCode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class Script {
    final private RequestHandler handler;

    public Script(Document baseDocument, String extension) {
        this.handler = request -> {
            Console console = new Console();
            Document document = baseDocument.clone();
            Elements httpScripts = document.getElementsByTag("httpscript");
            User user = new User(request.getCookies());

            for (Element httpScript : httpScripts) {

                Context context = Context.enter();
                String script = httpScript.html();

                try {
                    Scriptable scope = context.initStandardObjects();

                    Object wrappedConsole = Context.javaToJS(console, scope);
                    ScriptableObject.putProperty(scope, "console", wrappedConsole);

                    Function onRequestFunction = context.compileFunction(scope, script,
                            document.title(), 1, null);

                    onRequestFunction.call(context, scope, scope,
                            new Object[]{
                                    request,
                                    document,
                                    user
                            });

                    httpScript.replaceWith(new TextNode(""));
                } catch (Exception exception) {
                    System.out.println(
                            "An error occurred while executing HTTPScripts on page " + document.title());
                    exception.printStackTrace();
                    return new Response(StatusCode.STATUS_500);
                } finally {
                    Context.exit();
                }
            }
            Response response = new Response(document.toString());
            response.setResponseType(ResponseType.fromExtension(extension));
            for (Cookie cookie : user.getCookies()) {
                response.setCookie(cookie);
            }

            return response;
        };
    }

    public RequestHandler getHandler() {
        return handler;
    }
}