package org.example;

import lombok.Getter;

@Getter
public class PersonalSchedule {
    private Staff staff;
    private String workingTime;

    public PersonalSchedule(Staff staff, String startTime, String endTime) {
        this.staff = staff;
        this.workingTime = startTime + "-" + endTime;
    }
}
