package demoblaze.functional.base;

import lombok.Getter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class TestDataPropertyLoader {
    @Getter
    private final Properties properties = new Properties();

    public TestDataPropertyLoader() {
        String separator = File.separator;

        String path = System.getProperty("user.dir") + separator + "src" + separator + "test" + separator + "resources"
                + separator + "config" + separator + "demoBlaze.properties";
        try (InputStream stream = new FileInputStream(path)) {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}