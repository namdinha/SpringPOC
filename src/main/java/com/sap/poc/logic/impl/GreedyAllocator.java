package com.sap.poc.logic.impl;

import com.sap.poc.logic.Allocator;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Shift;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamMemberShift;
import com.sap.poc.services.CalendarDateService;
import com.sap.poc.services.TeamMemberShiftService;

import java.util.List;
import java.util.Map;

public class GreedyAllocator implements Allocator {

    private CalendarDateService calendarDateService;
    private TeamMemberShiftService teamMemberShiftService;

    public GreedyAllocator(CalendarDateService calendarDateService, TeamMemberShiftService teamMemberShiftService) {
        this.calendarDateService = calendarDateService;
        this.teamMemberShiftService = teamMemberShiftService;
    }

    @Override
    public void allocate(List<TeamMember> members) {
        deallocate(members);
        allocateDefinedShifts(members);
        allocateAnyShifts(members);
    }

    private void deallocate(List<TeamMember> members) {
        for(TeamMember member : members) {
            for(TeamMemberShift shift : member.getShifts()) {
                shift.setAllocatedShift(null);
                shift.getDate().setUsedCapacityToZero();
            }
        }
    }

    private void allocateDefinedShifts(List<TeamMember> members) {
        for(TeamMember member : members) {
            for(TeamMemberShift shift : member.getShifts()) {
                if(shift.getDesiredShift() != Shift.ANY && shift.isAvailable()) {
                    shift.setAllocatedShift(shift.getDesiredShift());
                    shift.getDate().addUsedCapacity(shift.getDesiredShift());
                }
            }
        }
    }

    private void allocateAnyShifts(List<TeamMember> members) {
        for(TeamMember member : members) {
            for(TeamMemberShift shift : member.getShifts()) {
                if(shift.getDesiredShift() == Shift.ANY && shift.isAvailable()) {
                    try {
                        Shift chosenShift = chooseShift(shift.getDate());
                        shift.setAllocatedShift(chosenShift);
                        shift.getDate().addUsedCapacity(chosenShift);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                calendarDateService.update(shift.getDate());
                teamMemberShiftService.update(shift);
            }
        }
    }

    private Shift chooseShift(CalendarDate date) throws Exception {
        CalendarDate reloadedDate = calendarDateService.getCalendarDateById(date.getId());
        Map<Shift, Integer> usedCapacity = reloadedDate.getUsedCapacity();
        Map<Shift, Integer> capacity = reloadedDate.getCapacity();
        for(Shift shift : Shift.getValidShifts()) {
            if(usedCapacity.get(shift) < capacity.get(shift))
                return shift;
        }
        throw new Exception("Can't allocate.");
    }
}
