package org.example;

import org.example.Interfaces.MenuItem;
import org.springframework.context.support.FileSystemXmlApplicationContext;

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

        DBDispatch.getInstance();

        Drink drink = new Drink("Кофе", "Coffee", 40.0);
        Order order = new Order(drink);

        DBDispatch.getInstance().editOrderById(order, 2);



        System.out.println(DBDispatch.getInstance().getPositions());

    }
}
