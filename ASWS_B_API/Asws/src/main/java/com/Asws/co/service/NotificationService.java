package com.Asws.co.service;

import java.util.List;

import com.Asws.co.domain.Notification;

public interface NotificationService {

    public Notification createNotification(Notification obj);
    public List<Notification> getAllNotifications();
    
}
