package com.sap.poc.daos;

import com.sap.poc.models.Notification;
import com.sap.poc.models.Team;

import java.util.List;

public interface NotificationDao {
    void create(Notification  notification);
    void refresh(Notification  notification);
    void update(Notification  notification);
    void delete(Notification  notification);
    Notification getNotificationById(int id);
    List<Notification> getNotificationsByTeam(Team team);
}
