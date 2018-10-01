package com.sap.poc.daos;


import com.sap.poc.models.Role;

import java.util.List;

public interface RoleDao {
    void create(Role role);
    Role getRoleByName(String roleName);
    List<Role> getRoles();
}
