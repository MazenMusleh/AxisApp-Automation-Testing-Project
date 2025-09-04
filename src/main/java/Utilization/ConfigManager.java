package Utilization;

/**
 * Singleton configuration manager that provides access to application properties.
 * Usage notes:
 * - Call {@code init(filePath)} once at the start of the test suite to load properties.
 * - Use {@code getInstance()} to access the single {@code ConfigManager} object.
 * - Call {@code getProperty(key)} to read a property.
 * - Call {@code setProperty(key, value)} to update a property.
 * @throws IllegalStateException If {@code getInstance()} is called before {@code init(filePath)}.
 */
public class ConfigManager {

    private static ConfigManager instance;
    private final PropertyFileReader propertyFileReader;

    // Private constructor to prevent direct instantiation
    private ConfigManager(String filePath) {
        propertyFileReader = new PropertyFileReader(filePath);
    }

    // Initialize ConfigManager with custom path
    public static void init(String filePath) {
        if (instance == null) {
            instance = new ConfigManager(filePath);
        }
    }

    // Get the only instance
    public static ConfigManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException("ConfigManager not initialized. Call init(filePath) first.");
        }
        return instance;
    }

    // Get property value
    public String getProperty(String key) {
        return propertyFileReader.getProperty(key);
    }

    // Set property value
    public void setProperty(String key, String value) {
        propertyFileReader.setProperty(key, value);
    }
}
