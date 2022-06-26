package com.localgp.localgp.repository;

import com.localgp.localgp.entity.DoctorSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Long> {
//    @Query("select w from WorkingPlan w where w.provider.id = :providerId")
//    WorkingPlan getWorkingPlanByProviderId(@Param("providerId") int providerId);

    @Query("select s from DoctorSchedule s where s.doctor.id = :doctorId")
    DoctorSchedule getDoctorScheduleByDoctorId(@Param("doctorId") long doctorId);
//    List<DoctorSchedule> findAllWithRequiredDateAfterAndByDoctorId(long doctorId, Date requiredDate);
//
//    List<DoctorSchedule> findAllByDoctorAfter(long doctorId, Date requiredDate);

}
