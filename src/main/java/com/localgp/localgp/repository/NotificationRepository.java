package com.localgp.localgp.repository;

import com.localgp.localgp.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query("select N from Notification N join N.userAuth u where u.id = :userAuthId and N.notificationStatus=false")
    List<Notification> getAllUnreadNotifications(@Param("userAuthId") long userAuthId);
}
