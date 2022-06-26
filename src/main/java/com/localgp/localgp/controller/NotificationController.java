package com.localgp.localgp.controller;

import com.localgp.localgp.entity.Notification;
import com.localgp.localgp.service.NotificationService;
import com.localgp.localgp.service.UserPrincipalDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
@CrossOrigin
public class NotificationController {

    private final NotificationService notificationService;
    private final UserPrincipalDetails userPrincipalDetails;

    public NotificationController(NotificationService notificationService, UserPrincipalDetails userPrincipalDetails) {
        this.notificationService = notificationService;
        this.userPrincipalDetails = userPrincipalDetails;
    }

    @GetMapping("/all")
    public List<Notification> showUserNotificationList(@RequestParam long userId) {
        return notificationService.getAll(userId);
    }

    @GetMapping("/allUnread")
    public List<Notification> showUserNotificationsUnread(@RequestParam long userId) {
        return notificationService.getUnReadNotifications(userId);
    }

    @GetMapping("/{notificationId}")
    public Notification showNotification(@PathVariable("notificationId") long notificationId, @PathVariable("userId") long userId) {
        Notification notification = notificationService.getNotificationById(notificationId);
        notificationService.markRead(notificationId, userId);
        return notification;
    }

    @GetMapping("/markAllAsRead")
    public String processMarkAllAsRead(@RequestParam long userId) {
        notificationService.markAllRead(userId);
        return "Read Successfully";
    }

    //@Scheduled(cron = "*/4 * * * * *")
    @GetMapping("/user/notifications")
    public int getUnreadNotificationsCount(@RequestParam long userId) {
        return notificationService.getUnReadNotifications(userId).size();
    }

}
