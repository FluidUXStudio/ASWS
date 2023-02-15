package com.Asws.co.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Asws.co.domain.Notification;
import com.Asws.co.repository.NotificationRepository;
import com.Asws.co.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    private NotificationRepository notificationRepository;




    @Override
    public Notification createNotification(Notification obj) {
        Notification n1 = notificationRepository.save(obj);
        return n1;
    }

    @Override
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }
    
}
