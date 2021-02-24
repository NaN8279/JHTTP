package io.github.nan8279.jhttp.request.raw;

/**
 * Request commands.
 */
public enum Command {
    GET("GET"),
    HEAD("HEAD"),
    POST("POST");

    final private String command;

    /**
     * @param command how the command will be given in a HTTP request.
     */
    Command(String command) {
        this.command = command;
    }

    /**
     * @return how the command will be given in a HTTP request.
     */
    @Override
    public String toString() {
        return command;
    }

    /**
     * Returns a command from a command in the HTTP request.
     *
     * @param command the command in the HTTP request.
     * @return the command.
     */
    public static Command fromString(String command) {
        for (Command cmd : Command.values()) {
            if (cmd.toString().equals(command)) {
                return cmd;
            }
        }
        return null;
    }
}
