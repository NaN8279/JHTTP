package io.github.nan8279.jhttp_script.import_manager;

public enum SysPackage {
    FILES("files"),
    CONSOLE("console");

    final private String moduleName;
    SysPackage(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getModuleName() {
        return moduleName;
    }

    public static String getPath(String moduleName) {
        for (SysPackage sysPackage : SysPackage.values()) {
            if (sysPackage.moduleName.equals(moduleName)) {
                return "/" + moduleName + ".js";
            }
        }
        return null;
    }
}
