package org.example;

import lombok.Getter;
import org.example.Interfaces.MenuItem;

import java.sql.Date;
import java.util.Calendar;

@Getter
public class Order {
    private MenuItem menuItem;
    private Date time;

    public Order(MenuItem menuItem) {
        this.menuItem = menuItem;

        java.util.Date currentDate = Calendar.getInstance().getTime();
        // Преобразуем его в java.sql.Date
        Date sqlDate = new Date(currentDate.getTime());

        this.time = sqlDate;
    }
}
