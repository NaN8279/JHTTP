// Made by 14 year old NaN8279
// |    |    |
// |    |    |
// |    |    |
// |     ----|
// |         |
// |         |
// |         |

package io.github.nan8279.jhttp_script;

import io.github.nan8279.jhttp.JHTTP;
import io.github.nan8279.jhttp.response.Response;
import io.github.nan8279.jhttp.response.response_types.ResponseType;
import io.github.nan8279.jhttp_script.script.Script;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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

            String name = page.getName();
            String extension = page.getName().split("\\.")[1];

            Document document = Jsoup.parse(page, "UTF-8");
            Elements httpScripts = document.getElementsByTag("httpscript");

            if (name.equals("index.html") || name.equals("index.htm")) {
                name = "";
            }

            if (httpScripts.size() != 0) {
                server.getManager().addRequestHandler(
                        new Script(document, extension).getHandler(), prefix + name);
            } else {
                server.getManager().addRequestHandler(
                        request -> {
                                Response response = Response.renderTemplate(page.getPath());
                                response.setResponseType(ResponseType.fromExtension(extension));
                                return response;
                            }, prefix + name);
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

        File moduleDir = new File("./modules");
        if (!moduleDir.isDirectory()) {
            if (!moduleDir.mkdirs()) {
                System.out.println("Couldn't create the pages directory!");
                return;
            }
        }

        int port = args.length == 0 ? 5000 : Integer.parseInt(args[0]);

        JHTTP server = new JHTTP(port);
        parseDirectory(pagesDir, server, "/");
        server.run();
    }
}
