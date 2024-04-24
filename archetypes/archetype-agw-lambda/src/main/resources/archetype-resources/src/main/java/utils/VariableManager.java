package ${package}.utils;

public class VariableManager {

    /**
     * Checks if a key exists in system properties or environment variables.
     *
     * @param key the key to check
     * @return true if the key exists in system properties or environment variables, false otherwise
     */
    public static boolean containsKey(String key) {
        boolean foundInSystemProperties = System.getProperty(key) != null;
        boolean foundInEnvironmentVariables = System.getenv(key) != null;
        return foundInSystemProperties || foundInEnvironmentVariables;
    }

    /**
     * Retrieves a configuration value from system properties or environment variables.
     * Returns null if the key is not found.
     *
     * @param key the key to look up
     * @return the value of the system property or environment variable, or null if neither is found
     */
    public static String get(String key) {
        String value = System.getProperty(key);
        if (value == null) {
            value = System.getenv(key);
        }
        return value;
    }

    /**
     * Retrieves a configuration value from system properties or environment variables.
     * Returns the default value if the key is not found.
     *
     * @param key the key to look up
     * @param defaultValue the default value to return if the key is not found
     * @return the value of the system property or environment variable, or the default value if neither is found
     */
    public static String getOrDefault(String key, String defaultValue) {
        String value = get(key);
        return value != null ? value : defaultValue;
    }
}
