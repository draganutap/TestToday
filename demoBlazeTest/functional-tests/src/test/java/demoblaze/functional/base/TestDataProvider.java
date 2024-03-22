package demoblaze.functional.base;

import java.util.Properties;

public class TestDataProvider {

    private static Properties TEST_DATA_PROPERTY;

    private static Properties getProperties() {
        if (TEST_DATA_PROPERTY == null) {
            TEST_DATA_PROPERTY = new TestDataPropertyLoader().getProperties();
        }
        return TEST_DATA_PROPERTY;
    }

    public static String getProperty(String propertyKey) {
        return getProperties().getProperty(propertyKey);
    }
}