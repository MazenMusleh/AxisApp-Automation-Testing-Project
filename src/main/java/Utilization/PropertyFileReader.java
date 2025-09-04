package Utilization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

    private final Properties properties;
    private final String filePath;

    /**
     * Creates a new {@code PropertyFileReader} and loads properties from the given file path.
     *
     * @param filePath absolute or relative path to the properties file
     * @throws RuntimeException if the file cannot be found or loaded
     */
    public PropertyFileReader(String filePath) {
        this.filePath = filePath;
        properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(filePath)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load properties file from path: " + filePath, e);
        }
    }

    /**
     * Retrieves the value for the given property key.
     *
     * @param key the property key
     * @return the value associated with the key, or {@code null} if not found
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    /**
     * Sets or updates a property key with the given value and writes
     * the change back to the properties file.
     *
     * @param key   the property key
     * @param value the property value
     * @throws RuntimeException if writing to the file fails
     */
    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            properties.store(fos, "Updated test data");
        } catch (IOException e) {
            throw new RuntimeException("Failed to write to properties file: " + filePath, e);
        }
    }
}
