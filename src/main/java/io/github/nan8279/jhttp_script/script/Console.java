package io.github.nan8279.jhttp_script.script;

import org.mozilla.javascript.Context;

import java.util.HashMap;

/**
 * JavaScript-like console object.
 */
public class Console {
    final private HashMap<String, Integer> counts = new HashMap<>();

    public Console() {
        counts.put("default", 0);
    }

    public void count() {
        counts.replace("default", counts.get("default") + 1);
        System.out.println("default: " + counts.get("default"));
    }

    public void count(String label) {
        if (counts.get(label) == null) {
            counts.put(label, 1);
        } else {
            counts.replace(label, counts.get(label) + 1);
        }

        System.out.println(label + ": " + counts.get(label));
    }

    public void countReset(String label) {
        counts.replace(label, 0);
    }

    public void log(Object message) {
        System.out.println(Context.toString(message));
    }
}
