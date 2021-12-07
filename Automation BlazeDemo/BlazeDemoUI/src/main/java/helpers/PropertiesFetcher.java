package helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFetcher {
    private static final Properties prop;

    static {
        prop = new Properties();

        try{
            prop.load(new FileInputStream(new File("./src/main/resources/properties/default.properties")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String get(String key){
        return get(key, null);
    }

    public static String get(String key, String value){
        return prop.getProperty(key, value);
    }

}
