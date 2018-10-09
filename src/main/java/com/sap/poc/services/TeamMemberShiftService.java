package com.sap.poc.services;

import com.sap.poc.models.TeamMemberShift;

public interface TeamMemberShiftService {

    void create(TeamMemberShift teamMemberShift);
    void refresh(TeamMemberShift teamMemberShift);
    void update(TeamMemberShift teamMemberShift);
    void delete(TeamMemberShift teamMemberShift);
    TeamMemberShift getTeamMemberShiftById(int id);
}
