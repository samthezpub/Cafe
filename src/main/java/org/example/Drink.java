package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Interfaces.MenuItem;

@AllArgsConstructor
@Getter
public class Drink implements MenuItem {
    private String nameRussian;
    private String nameEnglish;
    private double price;

    @Override
    public String getNameRussian() {
        return nameRussian;
    }

    @Override
    public String getNameEnglish() {
        return nameEnglish;
    }

    @Override
    public double getPrice() {
        return price;
    }

}
