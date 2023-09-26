package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Customer {
    private String name;
    private String birthDate;
    private String phone;
    private String email;
    private double discount;

}
