function readFile(path) {
    return java.nio.file.Files.readString(java.nio.file.Path.of(path));
}

function writeFile(path, text) {
    java.nio.file.Files.writeString(java.nio.file.Path.of(path),
                                        text,
                                        java.nio.charset.Charset.forName("UTF-8"),
                                        java.nio.file.StandardOpenOption.CREATE);
}