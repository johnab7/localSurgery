package com.localgp.localgp.service.serviceImplementation;

import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.entity.Notification;
import com.localgp.localgp.entity.UserAuth;
import com.localgp.localgp.repository.NotificationRepository;
import com.localgp.localgp.service.NotificationService;
import com.localgp.localgp.service.UserPrincipalDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Component
public class NotificationServiceImplementation implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UserPrincipalDetails userPrincipalDetails;

    @Override
    public void newNotification(String title, String message, UserAuth userAuth) {
        Notification notification= new Notification();
        notification.setTitle(title);
        notification.setMessage(message);
        notification.setNotificationTime(LocalDateTime.now());
        notification.setUserAuth(userAuth);
        notificationRepository.save(notification);

    }

    @Override
    public void markRead(long notificationId, long userAuthId) {
        Notification notification = notificationRepository.getById(notificationId);
        if (notification.getUserAuth().getId() == userAuthId) {
            notification.setNotificationStatus(true);
            notificationRepository.save(notification);
        } else {
            throw new org.springframework.security.access.AccessDeniedException("Unauthorized");
        }
    }

    @Override
    public void markAllRead(long userAuthId) {
        List<Notification> notifications = notificationRepository.getAllUnreadNotifications(userAuthId);
        for (Notification notification : notifications) {
            notification.setNotificationStatus(true);
            notificationRepository.save(notification);
        }
    }

    @Override
    public Notification getNotificationById(long notificationId) {
        return notificationRepository.getById(notificationId);
    }

    @Override
    public List<Notification> getAll(long userAuthId) {
        return userPrincipalDetails.getUserById(userAuthId).getNotifications();
    }

    @Override
    public List<Notification> getUnReadNotifications(long userAuthId) {
        return notificationRepository.getAllUnreadNotifications(userAuthId);
    }

    @Override
    public void newAppointmentNotification(Appointment appointment) {
        String title = "New appointment scheduled";
        String message = "New appointment scheduled with" + appointment.getPatient().getFirstName() + " " + appointment.getDoctor().getLastName() + " on " + appointment.getStartTime().toString();
        newNotification(title, message, appointment.getDoctor());
    }

    @Override
    public void appointmentCanceledByPatientNotification(Appointment appointment) {
        String title = "Appointment Canceled";
        String message = appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName() + " cancelled appointment scheduled at " + appointment.getStartTime().toString();
        newNotification(title, message, appointment.getDoctor());
    }

    @Override
    public void appointmentCanceledByDoctorNotification(Appointment appointment) {
        String title = "Appointment Canceled";
        String message = appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName() + " cancelled appointment scheduled at " + appointment.getStartTime().toString();
        newNotification(title, message, appointment.getPatient());
    }
}
