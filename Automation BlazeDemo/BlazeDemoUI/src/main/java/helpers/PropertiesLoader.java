package helpers;

import java.io.*;
import java.util.Properties;

public class PropertiesLoader {
    public static void setPropValue(String propKey, String propValue, String path) throws IOException {

        File file = new File(path);
        Properties properties = new Properties();
        FileInputStream fileInput = new FileInputStream(file);
        properties.load(fileInput);
        fileInput.close();
        properties.setProperty(propKey, propValue);
        properties.setProperty("latestKey", "latestValue");
        FileOutputStream fileOutput = new FileOutputStream(file);
        properties.store(fileOutput, null);
        fileOutput.close();

    }
}
