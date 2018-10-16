package com.sap.poc.services.impl;

import com.sap.poc.daos.NotificationDao;
import com.sap.poc.models.Notification;
import com.sap.poc.models.Team;
import com.sap.poc.services.NotificationService;

import javax.annotation.Resource;
import java.util.List;

public class NotificationServiceImp implements NotificationService {

    @Resource
    private NotificationDao hibernateNotificationDao;

    public NotificationServiceImp(NotificationDao hibernateNotificationDao) {
        this.hibernateNotificationDao = hibernateNotificationDao;
    }

    @Override
    public void create(Notification notification) {
        hibernateNotificationDao.create(notification);
    }

    @Override
    public void refresh(Notification notification) {
        hibernateNotificationDao.refresh(notification);
    }

    @Override
    public void update(Notification notification) {
        hibernateNotificationDao.update(notification);
    }

    @Override
    public void delete(Notification notification) {
        hibernateNotificationDao.delete(notification);
    }

    @Override
    public Notification getNotificationById(int id) {
        return hibernateNotificationDao.getNotificationById(id);
    }

    @Override
    public List<Notification> getNotificationsByTeam(Team team) {
        return hibernateNotificationDao.getNotificationsByTeam(team);
    }
}
