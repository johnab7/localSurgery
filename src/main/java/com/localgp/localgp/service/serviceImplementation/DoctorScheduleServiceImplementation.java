package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.DoctorSchedule;
import com.localgp.localgp.repository.DoctorScheduleRepository;
import com.localgp.localgp.service.DoctorScheduleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class DoctorScheduleServiceImplementation implements DoctorScheduleService {

    private final DoctorScheduleRepository doctorScheduleRepository;

    @Override
    public DoctorSchedule getDoctorScheduleByDoctorId(long doctorId) {
        DoctorSchedule doctorSchedule;
        doctorSchedule= doctorScheduleRepository.getDoctorScheduleByDoctorId(doctorId);
        return doctorSchedule;
    }

    @Override
    public void updateDoctorSchedule(DoctorSchedule updateSchedule) {
        DoctorSchedule doctorSchedule = doctorScheduleRepository.getById(updateSchedule.getId());
        doctorSchedule.getMonday().setWorkingHours(updateSchedule.getMonday().getWorkingHours());
        doctorSchedule.getTuesday().setWorkingHours(updateSchedule.getTuesday().getWorkingHours());
        doctorSchedule.getWednesday().setWorkingHours(updateSchedule.getWednesday().getWorkingHours());
        doctorSchedule.getThursday().setWorkingHours(updateSchedule.getThursday().getWorkingHours());
        doctorSchedule.getFriday().setWorkingHours(updateSchedule.getFriday().getWorkingHours());
        doctorSchedule.getSaturday().setWorkingHours(updateSchedule.getSaturday().getWorkingHours());
        doctorSchedule.getSunday().setWorkingHours(updateSchedule.getSunday().getWorkingHours());
        doctorScheduleRepository.save(doctorSchedule);
    }

}
