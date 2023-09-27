package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
public class Staff {
    private String name;
    private String phone;
    private String email;
    private Position position;


    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", position=" + position.getName() +
                '}';
    }
}
