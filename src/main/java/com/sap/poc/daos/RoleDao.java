package com.sap.poc.daos;


import com.sap.poc.models.Role;

public interface RoleDao {
    void create(Role role);
    Role getRoleByName(String roleName);
}
