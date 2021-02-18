package io.github.nan8279.jhttp_script.script;

import io.github.nan8279.jhttp.request.RequestHandler;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.response_types.ResponseType;
import io.github.nan8279.jhttp.response.status_code.StatusCode;
import io.github.nan8279.jhttp_script.DOM.DOMDocument;
import io.github.nan8279.jhttp_script.import_manager.ImportManager;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class Script {
    final private RequestHandler handler;
    private boolean executingModule = false;

    public Script(Document baseDocument, String extension) {
        this.handler = request -> {
            Document document = baseDocument.clone();
            Elements httpScripts = document.getElementsByTag("httpscript");

            for (Element httpScript : httpScripts) {
                try (Context context = Context.newBuilder("js")
                        .allowHostAccess(HostAccess.ALL)
                        .allowHostClassLookup(className -> executingModule)
                        .build()) {

                    String script = httpScript.wholeText();
                    ImportManager importManager = new ImportManager(context, this);
                    context.getBindings("js").putMember("importManager", importManager);

                    context.eval("js", script);
                    Value onRequestFunction = context.getBindings("js").getMember("onRequest");

                    onRequestFunction.execute(request, new DOMDocument(document));

                    httpScript.replaceWith(new TextNode(""));
                } catch (Exception exception) {
                    System.out.println(
                            "An error occurred while executing HTTPScripts on page " + document.title());
                    exception.printStackTrace();
                    return new Response(StatusCode.STATUS_500);
                }
            }
            Response response = new Response(document.toString());
            response.setResponseType(ResponseType.fromExtension(extension));

            return response;
        };
    }

    public RequestHandler getHandler() {
        return handler;
    }

    public void setExecutingModule(boolean executingModule) {
        this.executingModule = executingModule;
    }
}
