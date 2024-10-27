package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestUtils {

    public static String getProperty(String key) {
        Properties config = new Properties();

        try (InputStream inputStream = TestUtils.class.getClassLoader().getResourceAsStream("SwagLabsTestData.properties")) {
            if (inputStream == null) {
                throw new IOException("Property file 'SwagLabsTestData.properties' not found in the classpath");
            }
            config.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config.getProperty(key);
    }
}
