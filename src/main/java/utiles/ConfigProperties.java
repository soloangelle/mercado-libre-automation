package utiles;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
     public Properties readConfig(){

           Properties properties = new Properties();
           try {

                properties.load(new FileInputStream( ("src/main/resources/config.properties") ) );

           } catch (FileNotFoundException e) {
               e.printStackTrace();
           } catch (IOException e) {
               e.printStackTrace();
           }
         return properties;
     }
}
