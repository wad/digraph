package org.wadhome.digraph.setup;

public class Output {

    private static final StringBuilder outputCache = new StringBuilder();

    public static void show(String message) {
        show(message, false);
    }

    // Just consolidating all output here for convenience if we want to change it.
    public static void show(
            String message,
            boolean includeInCache) {

        if (message == null) {
            return;
        }

        if (includeInCache) {
            outputCache.append(message);
            outputCache.append("\n");
        }
        System.out.println(message);
    }

    public static String getCacheValue() {
        return outputCache.toString();
    }
}
