package com.sap.poc.logic.impl;

import com.sap.poc.logic.Allocator;
import com.sap.poc.models.CalendarDate;
import com.sap.poc.models.Shift;
import com.sap.poc.models.TeamMember;
import com.sap.poc.models.TeamMemberShift;

import java.util.List;
import java.util.Map;

public class GreedyAllocator implements Allocator {

    @Override
    public void allocate(List<TeamMember> members){
        allocateDefinedShifts(members);
        allocateAnyShifts(members);
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
            }
        }
    }

    private Shift chooseShift(CalendarDate date) throws Exception {
        Map<Shift, Integer> usedCapacity = date.getUsedCapacity();
        Map<Shift, Integer> capacity = date.getCapacity();
        for(Shift shift : Shift.getValidShifts()) {
            if(usedCapacity.get(shift) < capacity.get(shift))
                return shift;
        }
        throw new Exception("Can't allocate.");
    }
}
