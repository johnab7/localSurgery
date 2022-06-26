package com.localgp.localgp.model.viewModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
public class Period implements Comparable<Period>{
    private LocalTime startTime;
    private LocalTime endTime;

    public Period() {
    }

    @Override
    public int compareTo(Period period) {
        return this.getStartTime().compareTo(period.getStartTime());
    }

    @Override
    public boolean equals(Object wh) {
        if (this == wh) return true;
        if (wh == null || getClass() != wh.getClass()) return false;
        Period period = (Period) wh;
        return this.startTime.equals(period.getStartTime()) &&

                this.endTime.equals(period.getEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, endTime);
    }

    @Override
    public String toString() {
        return "Period{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    //    @Override
//    public String toString() {
//        return "WorkingHours{" +
//                "start=" + startTime +
//                ", end=" + endTime +
//                '}';
//    }
    //private final Period slotDuration =

}
