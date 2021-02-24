package io.github.nan8279.jhttp_script.import_manager;

import io.github.nan8279.jhttp.response.types.FileType;
import io.github.nan8279.jhttp_script.script.Script;
import org.graalvm.polyglot.Context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Objects;

public class ImportManager {
    final private Context context;
    final private Script script;

    public ImportManager(Context context, Script script) {
        this.context = context;
        this.script = script;
    }

    public void importModule(String moduleName) throws IOException {
        String path = SysPackage.getPath(moduleName);
        String module = null;

        if (path == null) {
            for (File customModule : Objects.requireNonNull(new File("./modules").listFiles())) {
                if (customModule.isDirectory()) {
                    continue;
                }
                String name = customModule.getName().split("\\.")[0];
                String extension = customModule.getName().split("\\.")[1];

                if (FileType.fromExtension(extension) == FileType.JAVASCRIPT) {
                    if (name.equals(moduleName)) {
                        module = Files.readString(customModule.toPath());
                        break;
                    }
                }
            }
            if (module == null) {
                throw new IllegalArgumentException("That module could not have been found.");
            }
        } else {
            InputStream moduleStream = getClass().getResourceAsStream(path);
            module = new String(moduleStream.readAllBytes());
        }

        script.setExecutingModule(true);
        context.eval("js", module);
        script.setExecutingModule(false);
    }
}
