package org.example;

import org.example.DAOs.CustomerDAO;
import org.example.DAOs.MenuItemDAO;
import org.example.DAOs.StaffDAO;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String configPath = "file:src/main/resources/spring-config.xml";
        var context = new FileSystemXmlApplicationContext(configPath);

        Connection connection;

        Properties properties = new Properties();
        String url = null;
        String username = null;
        String password = null;

        try {
            FileInputStream input = new FileInputStream("src/main/resources/db.properties");
            properties.load(input);
            input.close();

            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        StaffDAO staffDAO = new StaffDAO(connection);

        var waiters = staffDAO.getAllWaiters();
        for (int i = 0; i < waiters.size(); i++) {
            System.out.println(waiters.get(i).toString());
        }

        var conditers = staffDAO.getAllConditers();
        for (int i = 0; i < conditers.size(); i++) {
            System.out.println(conditers.get(i).toString());
        }


        CustomerDAO customerDAO = new CustomerDAO(connection);
        MenuItemDAO menuItemDAO = new MenuItemDAO(connection);

        var menuItems = menuItemDAO.getAll();
        for (int i = 0; i < menuItems.size(); i++) {
            System.out.println(menuItems.get(i).toString());
        }

    }
}
