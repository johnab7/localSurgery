package com.localgp.localgp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class DaySchedule{
    private Period workingHours;

//    private List<LocalDate> leaveDates;

    public List<Period> workingList(){
        ArrayList<Period> workingTimePeriods = new ArrayList<>();
        workingTimePeriods.add(getWorkingHours());
        return workingTimePeriods;
    }
    public DaySchedule(Period workingHours) {
        this.workingHours = workingHours;
    }

    public Period getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Period workingHours) {
        this.workingHours = workingHours;
    }
}
