package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.Interfaces.IMenuItem;

@AllArgsConstructor
@ToString
@Getter
public class Drink implements IMenuItem {
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
