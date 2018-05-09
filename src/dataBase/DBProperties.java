/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBProperties {
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;

    public void mkDbProperties() {
        
        try {
            output = new FileOutputStream("database.properties");
            properties.setProperty("host", "localhost");
            properties.setProperty("port", "3306");
            properties.setProperty("db", "510_lab04");
            properties.setProperty("user", "root");
            properties.setProperty("password", "pass");
            properties.store(output, null);
            output.close();
        } catch (FileNotFoundException ex) {
           ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public String loadPropertiesFile() {
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            return properties.getProperty("db");
        } catch (IOException e) {
            System.out.println("DDDD");
        }
        return "";
    }
}
