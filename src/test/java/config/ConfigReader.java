package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties PROPERTIES= new Properties();

    private static final String CONFIG_FILE= "config.properties";

    static {

        try(InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {

            if (inputStream == null) {
                throw new IllegalStateException("Configuration file not found" + CONFIG_FILE);
            }
            PROPERTIES.load(inputStream);
        }
        catch (IOException exception){
            throw  new ExceptionInInitializerError("Unable to load" + CONFIG_FILE+ ": "+ exception.getMessage());
        }
    }

    private ConfigReader(){
        //utility class
    }
    public static String getProperty(String key){
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()){
            return  systemValue.trim();
        }
        String fileValue = PROPERTIES.getProperty(key);
        if (fileValue == null || fileValue.isBlank()){
            throw new IllegalArgumentException("Missing configuration property : " +key);
        }
        return fileValue.trim();
    }

    public static int getIntProperty(String key){
        String value = getProperty(key);
        try{
            return Integer.parseInt(value);
        } catch (NumberFormatException exception ){
            throw new IllegalArgumentException("Configuration property must be a number: " +key + "=" + value,
                    exception );

        }
    }


    public static String getBaseUrl(){
        return getProperty("base.url");
    }
}
