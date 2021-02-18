Files = Java.type("java.nio.file.Files");
Path = Java.type("java.nio.file.Path");
Charset = Java.type("java.nio.charset.Charset");
StandardOpenOption = Java.type("java.nio.file.StandardOpenOption");

function readFile(path) {
    return Files.readString(Path.of(path));
}

function writeFile(path, text) {
    Files.writeString(Path.of(path),
                        text,
                        Charset.forName("UTF-8"),
                        StandardOpenOption.CREATE);
}