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

        Drink drink = new Drink("Кола", "Cola", 100.0);
        Order order = new Order(drink);
        System.out.println(order.getMenuItem().getNameRussian());

    }
}
