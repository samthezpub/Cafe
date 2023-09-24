package org.example;

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
    }
}
