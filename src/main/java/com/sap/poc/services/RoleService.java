package com.sap.poc.services;

import com.sap.poc.models.Role;

import java.util.List;

public interface RoleService {
    Role getRole(String roleName);
    void createRolesIfNotCreated(List<String> roleNames);
}
