/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thisarateafactory.database;

import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Isuru Hettiarachchi
 */
public class databaseHandler {
    private static String basePath;
    private static String path;
    
    static Connection connection = null;
   
    private static String url = null;
    private static String user = null;
    private static String pass = null;
    
    private databaseHandler(){
        getDatabaseDetails();
        createConnection();
    }//Constructor
    
    public static Connection getConnection(){
        if(connection != null)
            return connection;
        return createConnection();
    }//SharedConnectionInstance
    
    //createConnectionMethod
    private static Connection createConnection(){
        try {
            getDatabaseDetails();
            connection = (Connection) DriverManager.getConnection(url, user, pass);
            System.out.println("Database Connected");
            return connection;
        } catch (SQLException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private static void getDatabaseDetails(){
        try {
            basePath = new File("").getAbsolutePath();
            path = new File("src/credentials.properties").getAbsolutePath();
            
            Properties   properties = new Properties();
            properties .load(new FileInputStream(new File(path)));
            
            url = properties.getProperty("url");
            System.out.println(url);
            user = properties.getProperty("username");
            pass = properties.getProperty("password");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(databaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
