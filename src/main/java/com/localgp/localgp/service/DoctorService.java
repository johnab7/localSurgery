package com.localgp.localgp.service;


import com.localgp.localgp.entity.Doctor;
import com.localgp.localgp.model.dtoModel.DoctorDetailsModel;


import java.util.List;

public interface DoctorService {


//
    Doctor getById(long id) ;
    List<DoctorDetailsModel> getAllDoctors();

    Doctor getByUsername(String username);

    void createNewDoctor(DoctorDetailsModel doctorDetailsModel);

    void updateDoctorDetails(DoctorDetailsModel updateDoctorDetailsModel);


}
