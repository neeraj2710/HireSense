package in.hiresense.utils;

import java.util.ResourceBundle;

public class AppConfig {

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("application");

    public static String getProperty(String key){
        return RESOURCE_BUNDLE.getString(key);
    }

    public static String getProperty(String key, String defaultValue) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (Exception e) {
            return defaultValue;
        }
    }

}
