package com.localgp.localgp.service.serviceImplementation;
//
import com.localgp.localgp.entity.UserAuth;
import com.localgp.localgp.model.UpdatePasswordModel;
import com.localgp.localgp.repository.UserAuthRepository;
import com.localgp.localgp.security.UserPrincipal;
import com.localgp.localgp.service.UserPrincipalDetails;
//import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
import java.util.List;

//
//
@Service
public class UserPrincipalDetailsService implements UserPrincipalDetails {
//
    private UserAuthRepository userAuthRepository;
//
    PasswordEncoder passwordEncoder;

//
    @Autowired
    public UserPrincipalDetailsService(UserAuthRepository userAuthRepository) {
        this.userAuthRepository = userAuthRepository;
    }
//
//

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserAuth userAuth = this.userAuthRepository.findByUsername(s);
        UserPrincipal userPrincipal = new UserPrincipal(userAuth);

        return userPrincipal;
    }

    @Override
    public UserAuth getUserById(long userId) {
        return userAuthRepository.findById(userId);
    }

    @Override
    public UserAuth getUserByUsername(String username) {
        return userAuthRepository.findByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public List<UserAuth> getAllUsers() {
        return userAuthRepository.findAll();
    }

    @Override
    public void deleteUserById(long userId) {
        userAuthRepository.deleteById(userId);
    }

    @Override
    public void updatePassword(UpdatePasswordModel updatePasswordModel) {
        UserAuth userAuth = userAuthRepository.getById(updatePasswordModel.getUserId());
        userAuth.setPassword(passwordEncoder.encode(updatePasswordModel.getNewPassword()));
        userAuthRepository.save(userAuth);
    }

    @Override
    public boolean checkUsername(String username) {
        if(userAuthRepository.findByUsername(username) != null)
            return true;
        return false;
    }
//
//    @Override
//    public UserAuth register(UserRegistrationModel registrationModel) {
//        UserAuth user = this.modelMapper.map(registrationModel, UserAuth.class);
//        String passwordEncoded= this.passwordEncoder.encode(registrationModel.getPassword());
////      String encryptedPassword = this.bCryptPasswordEncoder.encode(registrationModel.getPassword());
//        user.setPassword(passwordEncoded);
//        return this.userAuthRepository.saveAndFlush(user);
//    }
//
//    @Override
//    public boolean updatePassword(UpdatePasswordModel updatePasswordModel) {
//        UserAuth user = this.userAuthRepository.findById(updatePasswordModel.getUserId());
//        String passwordEncoded= this.passwordEncoder.encode(updatePasswordModel.getPassword());
////      String encryptedPassword = this.bCryptPasswordEncoder.encode(updatePasswordModel.getPassword());
//        if (!this.passwordEncoder.matches(updatePasswordModel.getOldPassword(),
//                user.getPassword())) {
//            return false;
//        }
//
//        user.setPassword(passwordEncoded);
//
//        this.userAuthRepository.saveAndFlush(user);
//
//        return true;
//    }
//
//    @Override
//    public List<MedicalRecord> getMedRecords(long patientId) {
//        return this.medicalRecordsRepository.findByPatientId(patientId);
//    }
//
////    @Override
////    public List<DoctorScheduleModel> getDoctorSchedule(long doctorId) {
////        List<DoctorScheduleModel> models = new ArrayList<>();
////        Date date = new Date();
////        List<DoctorSchedule> schedules = this.scheduleRepository.findAllWithRequiredDateAfterAndByDoctorId(date, doctorId);
////
////        schedules.forEach(schedule ->{
////            models.add(this.modelMapper.map(schedule, DoctorScheduleModel.class));
////        });
////        return models;
////    }
//

}
