package com.localgp.localgp.service;

import com.localgp.localgp.entity.Appointment;
import com.localgp.localgp.entity.Notification;
import com.localgp.localgp.entity.UserAuth;

import java.util.List;

public interface NotificationService {

    void newNotification(String title, String message, UserAuth userAuth);

    void markRead(long notificationId, long userAuthId);

    void markAllRead(long userAuthId);

    Notification getNotificationById(long notificationId);

    List<Notification> getAll(long userAuthId);

    List<Notification> getUnReadNotifications(long userAuthId);

    void newAppointmentNotification(Appointment appointment);

    void appointmentCanceledByPatientNotification(Appointment appointment);

    void appointmentCanceledByDoctorNotification(Appointment appointment);
}
