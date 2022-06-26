package com.localgp.localgp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.localgp.localgp.model.DaySchedule;
import com.localgp.localgp.model.Period;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;


import javax.persistence.*;
import java.time.LocalTime;

@TypeDefs(@TypeDef(name = "json", typeClass = JsonStringType.class))
@Entity
@Table(name = "doctor_schedule")
public class DoctorSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @MapsId
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "monday")
    private DaySchedule monday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "tuesday")
    private DaySchedule tuesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "wednesday")
    private DaySchedule wednesday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "thursday")
    private DaySchedule thursday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "friday")
    private DaySchedule friday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "saturday")
    private DaySchedule saturday;

    @Type(type = "json")
    @Column(columnDefinition = "json", name = "sunday")
    private DaySchedule sunday;

//    private List<LocalDate> leaveDates;

    public static DoctorSchedule generateDefaultDoctorSchedule() {
        DoctorSchedule doctorSchedule = new DoctorSchedule();
        LocalTime defaultStartHour = LocalTime.parse("10:00");
        LocalTime defaultEndHour = LocalTime.parse("16:00");
        LocalTime weekendStartHour= LocalTime.parse("00:00");
        LocalTime weekendEndHour= LocalTime.parse("00:00");
        Period defaultWorkingHours = new Period(defaultStartHour, defaultEndHour);
        Period weekendWorkingHours = new Period(weekendStartHour, weekendEndHour);
        DaySchedule defaultDaySchedule = new DaySchedule(defaultWorkingHours);
        DaySchedule weekendDaySchedule = new DaySchedule(weekendWorkingHours);
        doctorSchedule.setMonday(defaultDaySchedule);
        doctorSchedule.setTuesday(defaultDaySchedule);
        doctorSchedule.setWednesday(defaultDaySchedule);
        doctorSchedule.setThursday(defaultDaySchedule);
        doctorSchedule.setFriday(defaultDaySchedule);
        doctorSchedule.setSaturday(weekendDaySchedule);
        doctorSchedule.setSunday(weekendDaySchedule);
        return doctorSchedule;
    }

    public DoctorSchedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DaySchedule getDay(String day) {
        switch (day) {
            case "monday":
                return monday;

            case "tuesday":
                return tuesday;

            case "wednesday":
                return wednesday;

            case "thursday":
                return thursday;

            case "friday":
                return friday;

            case "saturday":
                return saturday;

            case "sunday":
                return sunday;

            default:
                return null;
        }
    }

    public DaySchedule getMonday() {
        return monday;
    }

    public void setMonday(DaySchedule monday) {
        this.monday = monday;
    }

    public DaySchedule getTuesday() {
        return tuesday;
    }

    public void setTuesday(DaySchedule tuesday) {
        this.tuesday = tuesday;
    }

    public DaySchedule getWednesday() {
        return wednesday;
    }

    public void setWednesday(DaySchedule wednesday) {
        this.wednesday = wednesday;
    }

    public DaySchedule getThursday() {
        return thursday;
    }

    public void setThursday(DaySchedule thursday) {
        this.thursday = thursday;
    }

    public DaySchedule getFriday() {
        return friday;
    }

    public void setFriday(DaySchedule friday) {
        this.friday = friday;
    }

    public DaySchedule getSaturday() {
        return saturday;
    }

    public void setSaturday(DaySchedule saturday) {
        this.saturday = saturday;
    }

    public DaySchedule getSunday() {
        return sunday;
    }

    public void setSunday(DaySchedule sunday) {
        this.sunday = sunday;
    }


}
