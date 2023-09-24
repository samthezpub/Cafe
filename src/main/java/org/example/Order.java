package org.example;

import lombok.Getter;
import org.example.Interfaces.MenuItem;

import java.util.Date;

@Getter
public class Order {
    private MenuItem menuItem;
    private Date time;

    public Order(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.time = new Date();
    }
}
