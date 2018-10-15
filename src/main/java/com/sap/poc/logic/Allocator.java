package com.sap.poc.logic;

import com.sap.poc.models.TeamMember;

import java.util.List;

public interface Allocator {
    void allocate(List<TeamMember> members);
}
