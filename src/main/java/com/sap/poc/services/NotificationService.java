package com.sap.poc.services;

import com.sap.poc.models.Notification;
import com.sap.poc.models.Team;

import java.util.List;

public interface NotificationService {
    void create(Notification notification);
    void refresh(Notification  notification);
    void update(Notification  notification);
    void delete(Notification  notification);
    Notification getNotificationById(int id);
    List<Notification> getNotificationsByTeam(Team team);
}
