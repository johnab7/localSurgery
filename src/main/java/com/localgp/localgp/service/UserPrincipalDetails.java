package com.localgp.localgp.service;


import com.localgp.localgp.entity.UserAuth;
import com.localgp.localgp.model.UpdatePasswordModel;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.List;


public interface UserPrincipalDetails extends UserDetailsService {
    UserAuth getUserById(long userId);

    UserAuth getUserByUsername(String username);

    List<UserAuth> getAllUsers();

    void deleteUserById(long userId);

    void updatePassword(UpdatePasswordModel updatePasswordModel);

    boolean checkUsername(String username);
//    UserAuth register(UserRegistrationModel registrationModel);
//
//    boolean updatePassword(UpdatePasswordModel changePasswordModel);
//
//    List<MedicalRecord> getMedRecords(long patientId);

//    List<DoctorScheduleModel> getDoctorSchedule(long doctorId);
}
