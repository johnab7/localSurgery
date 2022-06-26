package com.localgp.localgp.service;

import com.localgp.localgp.entity.DoctorSchedule;

public interface DoctorScheduleService {

    DoctorSchedule getDoctorScheduleByDoctorId(long doctorId);
    void updateDoctorSchedule(DoctorSchedule updateSchedule);
}
