package org.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.Enums.PositionsEnum;

@AllArgsConstructor
@Getter
public class Staff {
    private String name;
    private String phone;
    private String email;
    private PositionsEnum position;

}
