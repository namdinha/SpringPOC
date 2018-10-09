package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamMemberShiftDao;
import com.sap.poc.models.TeamMemberShift;
import com.sap.poc.services.TeamMemberShiftService;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import javax.annotation.Resource;

public class TeamMemberShiftServiceImp implements TeamMemberShiftService {

    @Resource
    private TeamMemberShiftDao hibernateTeamMemberShiftDao;

    public TeamMemberShiftServiceImp(TeamMemberShiftDao hibernateTeamMemberShiftDao) {
        this.hibernateTeamMemberShiftDao = hibernateTeamMemberShiftDao;
    }

    @Override
    public void create(TeamMemberShift teamMemberShift) {
        hibernateTeamMemberShiftDao.create(teamMemberShift);
    }

    @Override
    public void refresh(TeamMemberShift teamMemberShift) {
        hibernateTeamMemberShiftDao.refresh(teamMemberShift);
    }

    @Override
    public void update(TeamMemberShift teamMemberShift) {
        hibernateTeamMemberShiftDao.update(teamMemberShift);
    }

    @Override
    public void delete(TeamMemberShift teamMemberShift) {
        hibernateTeamMemberShiftDao.delete(teamMemberShift);
    }

    @Override
    public TeamMemberShift getTeamMemberShiftById(int id) {
        return null;
    }
}
