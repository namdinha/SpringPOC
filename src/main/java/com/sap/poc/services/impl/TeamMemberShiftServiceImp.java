package com.sap.poc.services.impl;

import com.sap.poc.daos.TeamMemberShiftDao;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Team;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamMemberShift;
import com.sap.poc.services.TeamMemberShiftService;
import com.sap.poc.services.UserService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

public class TeamMemberShiftServiceImp implements TeamMemberShiftService {

    @Resource
    private TeamMemberShiftDao hibernateTeamMemberShiftDao;
    @Resource
    private UserService userService;

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
        return hibernateTeamMemberShiftDao.getTeamMemberShiftById(id);
    }

    @Override
    public void createShifts(Set<TeamMemberShift> shifts) {
        for(TeamMemberShift shift : shifts) {
            this.create(shift);
        }
    }

    @Override
    public void createShiftsOfMembers(Set<TeamMember> members) {
        for(TeamMember member : members) {
            this.createShifts(member.getShifts());
        }
    }

    @Override
    public List<TeamMemberShift> getTeamMemberShiftsByMember(TeamMember member) {
        return hibernateTeamMemberShiftDao.getTeamMemberShiftsByMember(member);
    }

    @Override
    public void changeAvailabilityByDate(CalendarDate date, boolean availability) {
        List<TeamMemberShift> shifts = hibernateTeamMemberShiftDao.getTeamMemberShiftsByCalendarDate(date);
        for (TeamMemberShift shift : shifts) {
            shift.setAvailable(availability);
            shift.setAllocatedShift(null);
            this.update(shift);
        }
    }

    @Override
    public void updateShiftsByMembers(List<TeamMember> members) {
        for(TeamMember member : members) {
            for(TeamMemberShift shift : member.getShifts()) {
                this.update(shift);
            }
        }
    }

    @Override
    public List<TeamMemberShift> getTeamMemberShiftsByCalendarDate(CalendarDate calendarDate) {
        return hibernateTeamMemberShiftDao.getTeamMemberShiftsByCalendarDate(calendarDate);
    }
}
