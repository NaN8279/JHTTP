package io.github.nan8279.jhttp_script;

import io.github.nan8279.jhttp.JHTTP;
import io.github.nan8279.jhttp.request.Request;
import io.github.nan8279.jhttp.request.RequestHandler;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.status_code.StatusCode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Server {

    private static void parseDirectory(File pagesDir, JHTTP server, String prefix) throws IOException {
        for (File page : Objects.requireNonNull(pagesDir.listFiles())) {
            if (page.isDirectory()) {
                parseDirectory(page, server, prefix + page.getName() + "/");
                continue;
            }

            String name = page.getName().split("\\.")[0];

            Document document = Jsoup.parse(page, "UTF-8");
            Elements httpScripts = document.getElementsByTag("httpscript");

            if (name.equals("index")) {
                name = "";
            }

            if (httpScripts.size() != 0) {

                String finalName = name;
                RequestHandler handler = request -> {
                    Document documentCopy = document.clone();
                    Elements httpScriptsCopy = documentCopy.getElementsByTag(
                            "httpscript");

                    for (Element httpScript : httpScriptsCopy) {
                        Context context = Context.enter();
                        String script = httpScript.html();

                        try {
                            Scriptable scope = context.initStandardObjects();
                            Function onRequestFunction = context.compileFunction(scope, script,
                                    finalName, 1, null);

                            Object result = onRequestFunction.call(context, scope, scope,
                                    new Request[]{request});

                            httpScript.replaceWith(new TextNode(Context.toString(result)));
                        } catch (Exception exception) {
                            System.out.println("An error occurred while rendering page " + finalName);
                            exception.printStackTrace();
                            return new Response(StatusCode.STATUS_500);
                        } finally {
                            Context.exit();
                        }
                    }
                    return new Response(documentCopy.toString());
                };

                server.getManager().addRequestHandler(handler, prefix + name);
            } else {
                server.getManager().addRequestHandler(
                        request -> Response.renderTemplate(page.getPath()), prefix + name);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        File pagesDir = new File("./pages");
        if (!pagesDir.isDirectory()) {
            if (!pagesDir.mkdirs()) {
                System.out.println("Couldn't create the pages directory!");
                return;
            }
        }

        JHTTP server = new JHTTP(args.length == 0 ? 5000 : Integer.parseInt(args[0]));
        parseDirectory(pagesDir, server, "/");
        server.run();
    }
}
